package com.categoriesservice.categories.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Category Entity - Representa una categoría para organizar tareas
 */
@Entity
@Table(name = "categories")
@Data
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String color;
    
    @Column(nullable = false)
    private Long userId;
}
