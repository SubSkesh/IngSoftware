package com.example.simple_inventory_system.controller;

import com.example.simple_inventory_system.model.Product;
import com.example.simple_inventory_system.model.User;
import com.example.simple_inventory_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.simple_inventory_system.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    // Recupera tutti i prodotti associati all'utente corrente
    @GetMapping
    public List<Product> getAllProducts(Principal principal) {
        // Trova l'utente autenticato basandosi sul nome utente dal Principal
        User user = userService.findByUsername(principal.getName());
        return productRepository.findByUser(user);
    }

    // Elimina tutti i prodotti dell'utente corrente
    @DeleteMapping
    public ResponseEntity<?> deleteAllProductsForUser(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Product> products = productRepository.findByUser(user);

        if (!products.isEmpty()) {
            productRepository.deleteAll(products);
            return ResponseEntity.ok("{\"message\": \"All products deleted successfully.\"}");
        } else {
            return ResponseEntity.status(404).body("{\"message\": \"No products found to delete.\"}");
        }
    }

    // Recupera un prodotto specifico in base all'ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product prod = product.get();
            prod.setUser(null); // Rimuove i dati dell'utente per non esporli nel risultato
            return prod;
        }
        return null; // Se non trova il prodotto, ritorna null
    }

    // Crea un nuovo prodotto associato all'utente corrente
    @PostMapping
    public Product createProduct(@RequestBody Product product, Principal principal) {
        // Controlla che la quantità sia almeno 1
        if (product.getQuantity() < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1!");
        }
        // Controlla che il prezzo sia positivo
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price must be positive!");
        }

        // Associa il prodotto all'utente autenticato
        User user = userService.findByUsername(principal.getName());
        product.setUser(user);

        return productRepository.save(product); // Salva e restituisce il prodotto appena creato
    }

    // Aggiorna un prodotto specifico in base all'ID
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        // Controlla che la quantità sia almeno 1
        if (updatedProduct.getQuantity() < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1!");
        }
        // Trova e aggiorna il prodotto esistente
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setQuantity(updatedProduct.getQuantity());
            product.setPrice(updatedProduct.getPrice());
            return productRepository.save(product);
        }).orElse(null); // Se non trova il prodotto, ritorna null
    }

    // Elimina un prodotto specifico in base all'ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepository.deleteById(id);
            // Restituisce un messaggio di conferma con il nome del prodotto eliminato
            return ResponseEntity.ok("{\"message\": \"Product deleted successfully.\", \"deletedProduct\": \"" + product.getName() + "\"}");
        } else {
            // Se non trova il prodotto, restituisce un messaggio di errore
            return ResponseEntity.status(404).body("{\"message\": \"Product not found.\"}");
        }
    }
}
