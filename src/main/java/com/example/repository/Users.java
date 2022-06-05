package com.example.repository;

import com.example.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Users extends JpaRepository<Registration, Integer> {

    Registration findByName(String username);
}