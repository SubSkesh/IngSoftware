package com.example.simple_inventory_system.controller;

import com.example.simple_inventory_system.model.Product;
import com.example.simple_inventory_system.model.User;
import com.example.simple_inventory_system.repository.ProductRepository;

import com.example.simple_inventory_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository; // Accesso ai prodotti nel database

    @Autowired
    private UserService userService; // Servizio per gestire gli utenti

    // Mostra i prodotti di un utente
    @GetMapping("/products")
    public String showProducts(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName()); // Ottiene l'utente loggato
        List<Product> products = productRepository.findByUser(user); // Trova i prodotti associati all'utente
        model.addAttribute("products", products); // Passa i prodotti al modello per visualizzarli
        return "products"; // Ritorna la pagina dei prodotti
    }

    // Aggiunge un nuovo prodotto
    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name,
                             @RequestParam int quantity,
                             @RequestParam double price,
                             Principal principal,
                             Model model) {
        // Verifica se il prezzo è valido
        if (price < 0) {
            model.addAttribute("error", "Price Cannot be less than 0");
        }

        // Verifica se la quantità è valida
        if (quantity < 1) {
            model.addAttribute("error", "Quantity must be at least 1.");
            return "products"; // Ritorna alla pagina se ci sono errori
        }

        // Aggiunge il prodotto al database per l'utente loggato
        User user = userService.findByUsername(principal.getName());
        Product product = new Product(name, quantity, price, user);
        productRepository.save(product); // Salva il prodotto
        return "redirect:/products"; // Ricarica la pagina dei prodotti
    }

    // Elimina un prodotto
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        Product product = productRepository.findById(id).orElse(null); // Trova il prodotto per ID
        // Verifica che l'utente abbia il permesso di eliminare il prodotto
        if (product != null && product.getUser().getUsername().equals(principal.getName())) {
            productRepository.deleteById(id); // Elimina il prodotto
        }
        return "redirect:/products"; // Ricarica la pagina dei prodotti
    }

    // Mostra la pagina per modificare un prodotto
    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable Long id, Model model, Principal principal) {
        Product product = productRepository.findById(id).orElse(null); // Trova il prodotto per ID
        // Verifica che l'utente possa modificare il prodotto
        if (product != null && product.getUser().getUsername().equals(principal.getName())) {
            model.addAttribute("product", product); // Passa il prodotto al modello
            return "editProduct"; // Mostra la pagina di modifica del prodotto
        }
        return "redirect:/products"; // Se non può modificare, ritorna alla pagina dei prodotti
    }

    // Aggiorna i dettagli di un prodotto esistente
    @PostMapping("/updateProduct")
    public String updateProduct(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam int quantity,
                                @RequestParam double price,
                                Principal principal,
                                Model model) {
        // Verifica la validità della quantità
        if (quantity < 1) {
            model.addAttribute("error", "Quantity must be at least 1.");
            return "editProduct"; // Ritorna alla pagina di modifica se ci sono errori
        }

        // Trova il prodotto e verifica che l'utente possa modificarlo
        Product product = productRepository.findById(id).orElse(null);
        if (product != null && product.getUser().getUsername().equals(principal.getName())) {
            product.setName(name); // Aggiorna il nome
            product.setQuantity(quantity); // Aggiorna la quantità
            product.setPrice(price); // Aggiorna il prezzo
            productRepository.save(product); // Salva le modifiche nel database
        }
        return "redirect:/products"; // Ritorna alla pagina dei prodotti
    }
}
