package com.example.repository;

import com.example.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Product extends JpaRepository<Products, Integer> {
    Products findByName(String name);

    Products deleteById(int id);

}
