package com.example.apiauthcrud.repository;

import com.example.apiauthcrud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
