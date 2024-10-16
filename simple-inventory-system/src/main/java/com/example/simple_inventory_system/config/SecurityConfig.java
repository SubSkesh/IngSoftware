package com.example.simple_inventory_system.config;

import com.example.simple_inventory_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Definiamo le regole di sicurezza per l'applicazione
        http
                .csrf().disable() // Disattiviamo la protezione CSRF per semplificare le cose (non sempre ideale in produzione)
                .authorizeRequests()
                .antMatchers("/register", "/login", "/static/**").permitAll() // Chiunque può accedere alle pagine di registrazione e login
                .antMatchers("/api/products/**").authenticated() // Per accedere alle API dei prodotti è necessario essere autenticati
                .anyRequest().authenticated() // Tutto il resto richiede autenticazione
                .and()
                .httpBasic() // Abilita l'autenticazione Basic (utente e password nell'header della richiesta)
                .and()
                .formLogin()
                .loginPage("/login") // Usiamo una pagina personalizzata per il login
                .defaultSuccessUrl("/products", true) // Dopo un login riuscito, reindirizziamo alla pagina dei prodotti
                .failureUrl("/login?error=true") // Se il login fallisce, rimandiamo alla pagina di login con un messaggio di errore
                .permitAll()
                .and()
                .logout()
                .permitAll(); // Chiunque può effettuare il logout
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Usiamo BCrypt per criptare le password. Questo rende difficile decifrarle se vengono compromesse.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // Configuriamo l'autenticazione usando il nostro servizio utente e BCrypt
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService((UserDetailsService) userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Indichiamo al sistema di usare il nostro provider di autenticazione
        auth.authenticationProvider(authenticationProvider());
    }
}
