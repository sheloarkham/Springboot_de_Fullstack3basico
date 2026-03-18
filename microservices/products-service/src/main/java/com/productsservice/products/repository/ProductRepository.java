package com.productsservice.products.repository;

import com.productsservice.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository - Capa de acceso a datos
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * Busca productos por categoría.
     * SQL generado: SELECT * FROM products WHERE category = ?
     */
    List<Product> findByCategory(String category);
    
    /**
     * Busca productos por nombre (coincidencia parcial).
     * SQL generado: SELECT * FROM products WHERE name LIKE %?%
     */
    List<Product> findByNameContainingIgnoreCase(String name);
}
