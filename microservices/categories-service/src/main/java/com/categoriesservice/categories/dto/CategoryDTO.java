package com.categoriesservice.categories.dto;

import lombok.Data;

/**
 * Category Data Transfer Object
 */
@Data
public class CategoryDTO {
    
    private Long id;
    private String name;
    private String color;
    private Long userId;
}
