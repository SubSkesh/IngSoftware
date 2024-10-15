package com.example.simple_inventory_system.service;

import com.example.simple_inventory_system.model.Product;
import com.example.simple_inventory_system.model.User;
import com.example.simple_inventory_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Aggiungi un prodotto associato a un utente
    public void addProduct(String name, int quantity, double price, User user) {
        Product product = new Product(name, quantity, price, user);
        productRepository.save(product);
    }

    // Trova tutti i prodotti di un determinato utente
    public List<Product> findByUser(User user) {
        return productRepository.findByUser(user);
    }

    // Altri metodi CRUD (opzionali)
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}