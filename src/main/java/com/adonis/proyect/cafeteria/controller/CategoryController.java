package com.adonis.proyect.cafeteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adonis.proyect.cafeteria.model.Category;
import com.adonis.proyect.cafeteria.repository.CategoriRepository;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  /*   @Autowired
    private final CategoriRepository categoryRepository;

    public CategoryController(CategoriRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> list() {
        return categoryRepository.findAll();
    } */
}

