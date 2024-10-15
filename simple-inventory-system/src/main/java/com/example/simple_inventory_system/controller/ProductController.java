package com.example.simple_inventory_system.controller;


import com.example.simple_inventory_system.model.Product;
import com.example.simple_inventory_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = (List<Product>) productRepository.findAll();
        System.out.println("Numero di prodotti trovati: " + products.size());  // Per verificare quanti prodotti sono trovati
        model.addAttribute("products", products);
        return "products";
    }


    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name,
                             @RequestParam int quantity,
                             @RequestParam double price) {
        Product product = new Product(name, quantity, price);
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam int quantity,
                                @RequestParam double price) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(name);
            product.setQuantity(quantity);
            product.setPrice(price);
            productRepository.save(product);
        }
        return "redirect:/products";
    }
}
