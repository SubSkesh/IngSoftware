package com.example.simple_inventory_system.controller;

import com.example.simple_inventory_system.model.User;
import com.example.simple_inventory_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    private UserService userService;

    // Endpoint per registrare un nuovo utente
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Chiama il servizio per registrare un nuovo utente usando username e password
            User newUser = userService.registerNewUser(user.getUsername(), user.getPassword());
            // Se la registrazione va a buon fine, restituisce l'utente appena creato
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            // Se c'è un errore, restituisce un messaggio di errore
            return ResponseEntity.badRequest().body("Errore nella registrazione: " + e.getMessage());
        }
    }

    // Endpoint per effettuare il login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {
        // Verifica se l'utente è autenticato
        boolean authenticated = userService.authenticate(username, password);
        if (authenticated) {
            // Se le credenziali sono corrette, restituisce un messaggio di successo
            return ResponseEntity.ok("Login effettuato con successo");
        } else {
            // Se le credenziali sono errate, restituisce un errore 401 (non autorizzato)
            return ResponseEntity.status(401).body("Credenziali errate");
        }
    }
}
