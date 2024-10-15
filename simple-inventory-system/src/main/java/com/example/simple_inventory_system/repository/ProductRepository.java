package com.example.simple_inventory_system.repository;


import com.example.simple_inventory_system.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> { //questa mi fornicsce le op di crud
}
