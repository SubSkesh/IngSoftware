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

    @GetMapping
    public List<Product> getAllProducts(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return productRepository.findByUser(user);
    }
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


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product prod = product.get();
            prod.setUser(null); // Rimuove i dati dell'utente prima di restituire il prodotto
            return prod;
        }
        return null;
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product, Principal principal) {
        if (product.getQuantity() < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1!");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price must be positive!");
        }

        User user = userService.findByUsername(principal.getName());
        product.setUser(user); // Associa il prodotto all'utente corrente

        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        if (updatedProduct.getQuantity() < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1!");
        }
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setQuantity(updatedProduct.getQuantity());
            product.setPrice(updatedProduct.getPrice());
            return productRepository.save(product);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepository.deleteById(id);
            return ResponseEntity.ok("{\"message\": \"Product deleted successfully.\", \"deletedProduct\": \"" + product.getName() + "\"}");
        } else {
            return ResponseEntity.status(404).body("{\"message\": \"Product not found.\"}");
        }
    }
}
