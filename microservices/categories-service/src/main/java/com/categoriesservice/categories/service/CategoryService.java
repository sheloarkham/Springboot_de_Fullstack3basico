package com.categoriesservice.categories.service;

import com.categoriesservice.categories.dto.CategoryDTO;
import com.categoriesservice.categories.model.Category;
import com.categoriesservice.categories.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Category Service - Lógica de negocio para gestión de categorías
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<CategoryDTO> listarTodas() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public CategoryDTO guardar(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }
    
    public Optional<CategoryDTO> obtenerPorId(Long id) {
        return categoryRepository.findById(id)
                .map(this::convertToDTO);
    }
    
    public List<CategoryDTO> obtenerPorUsuario(Long userId) {
        return categoryRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CategoryDTO> buscarPorNombre(String nombre) {
        return categoryRepository.findByNameContainingIgnoreCase(nombre).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<CategoryDTO> actualizar(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(categoryDTO.getName());
                    category.setColor(categoryDTO.getColor());
                    category.setUserId(categoryDTO.getUserId());
                    Category updated = categoryRepository.save(category);
                    return convertToDTO(updated);
                });
    }
    
    public boolean eliminar(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setColor(category.getColor());
        dto.setUserId(category.getUserId());
        return dto;
    }
    
    private Category convertToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setColor(dto.getColor());
        category.setUserId(dto.getUserId());
        return category;
    }
}
