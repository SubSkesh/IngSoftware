package com.example.simple_inventory_system.controller;

import com.example.simple_inventory_system.model.User;
import com.example.simple_inventory_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService; // Servizio per la gestione degli utenti

    // Mostra il form di registrazione
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Ritorna la pagina del form di registrazione
    }

    // Gestisce la registrazione di un nuovo utente
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        try {
            // Registra un nuovo utente con username e password
            User user = userService.registerNewUser(username, password);

            // Autentica automaticamente l'utente appena registrato
            UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()); // Crea un token di autenticazione
            SecurityContextHolder.getContext().setAuthentication(authentication); // Imposta l'utente come autenticato

            return "redirect:/products"; // Dopo la registrazione, reindirizza alla pagina dei prodotti
        } catch (Exception e) {
            // Se la registrazione fallisce, mostra un messaggio di errore
            model.addAttribute("error", "Registrazione fallita: " + e.getMessage());
            return "register"; // Ritorna alla pagina di registrazione
        }
    }
}
