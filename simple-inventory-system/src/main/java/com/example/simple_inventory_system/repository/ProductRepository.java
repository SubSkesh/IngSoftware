package com.example.simple_inventory_system.repository;

import com.example.simple_inventory_system.model.Product;
import com.example.simple_inventory_system.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    // Trova tutti i prodotti associati a un particolare utente
    List<Product> findByUser(User user);
}
