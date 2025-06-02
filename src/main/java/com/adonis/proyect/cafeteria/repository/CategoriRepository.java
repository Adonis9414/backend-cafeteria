package com.adonis.proyect.cafeteria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adonis.proyect.cafeteria.model.Category;

public interface CategoriRepository  extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);

    
} 
