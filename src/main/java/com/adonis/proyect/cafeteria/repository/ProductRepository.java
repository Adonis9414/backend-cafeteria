package com.adonis.proyect.cafeteria.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.adonis.proyect.cafeteria.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String categoryName);
}
