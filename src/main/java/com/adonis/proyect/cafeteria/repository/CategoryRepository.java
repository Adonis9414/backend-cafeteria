package com.adonis.proyect.cafeteria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adonis.proyect.cafeteria.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
