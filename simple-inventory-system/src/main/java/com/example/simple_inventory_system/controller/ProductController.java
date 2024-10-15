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
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/products")
    public String showProducts(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Product> products = productRepository.findByUser(user);
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name,
                             @RequestParam int quantity,
                             @RequestParam double price,
                             Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Product product = new Product(name, quantity, price, user);
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null && product.getUser().getUsername().equals(principal.getName())) {
            productRepository.deleteById(id);
        }
        return "redirect:/products";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable Long id, Model model, Principal principal) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null && product.getUser().getUsername().equals(principal.getName())) {
            model.addAttribute("product", product);
            return "editProduct";
        }
        return "redirect:/products";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam int quantity,
                                @RequestParam double price,
                                Principal principal) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null && product.getUser().getUsername().equals(principal.getName())) {
            product.setName(name);
            product.setQuantity(quantity);
            product.setPrice(price);
            productRepository.save(product);
        }
        return "redirect:/products";
    }
}
