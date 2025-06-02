package com.adonis.proyect.cafeteria.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.adonis.proyect.cafeteria.dto.ProductDto;
import com.adonis.proyect.cafeteria.model.Category;
import com.adonis.proyect.cafeteria.model.Product;
import com.adonis.proyect.cafeteria.repository.CategoryRepository;
import com.adonis.proyect.cafeteria.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public List<Product> list(@RequestParam Optional<String> category) {
        logger.info("Solicitando productos. Filtro categoría: {}", category.orElse("Ninguno"));
        return category.map(productRepository::findByCategoryName)
                    .orElse(productRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody ProductDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        logger.info("Creando producto: {}", product.getName());
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest().body("El precio no puede ser negativo");
        }        
        return ResponseEntity.ok(productRepository.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product updated) {
        logger.info("Actualizando producto con ID: {}", id);
        if (updated.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest().body("El precio no puede ser negativo");
        }
        return productRepository.findById(id)
            .map(p -> {
                p.setName(updated.getName());
                p.setDescription(updated.getDescription());
                p.setPrice(updated.getPrice());
                p.setCategory(updated.getCategory());
                return ResponseEntity.ok(productRepository.save(p));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            logger.info("Producto con ID {} eliminado", id);
            // Si se elimina correctamente, retornamos un 200 OK
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
