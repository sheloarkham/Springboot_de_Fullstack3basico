package com.categoriesservice.categories.repository;

import com.categoriesservice.categories.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Category Repository - Interfaz para acceso a datos de categorías
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    List<Category> findByUserId(Long userId);
    List<Category> findByNameContainingIgnoreCase(String name);
}
