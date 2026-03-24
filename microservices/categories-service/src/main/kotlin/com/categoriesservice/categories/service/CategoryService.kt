package com.categoriesservice.categories.service

import com.categoriesservice.categories.dto.CategoryDTO
import com.categoriesservice.categories.dto.toDTO
import com.categoriesservice.categories.dto.toEntity
import com.categoriesservice.categories.repository.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CategoryService(
    private val repository: CategoryRepository
) {
    
    fun findAll(): List<CategoryDTO> = 
        repository.findAll().map { it.toDTO() }
    
    fun findAllActive(): List<CategoryDTO> = 
        repository.findByActiveTrue().map { it.toDTO() }
    
    fun findById(id: Long): CategoryDTO = 
        repository.findById(id)
            .map { it.toDTO() }
            .orElseThrow { NoSuchElementException("Category not found with id: $id") }
    
    fun create(dto: CategoryDTO): CategoryDTO {
        if (repository.existsByName(dto.name)) {
            throw IllegalArgumentException("Category with name '${dto.name}' already exists")
        }
        val entity = dto.toEntity()
        val saved = repository.save(entity)
        return saved.toDTO()
    }
    
    fun update(id: Long, dto: CategoryDTO): CategoryDTO {
        val existing = repository.findById(id)
            .orElseThrow { NoSuchElementException("Category not found with id: $id") }
        
        // Verificar nombre duplicado si se está cambiando
        if (existing.name != dto.name && repository.existsByName(dto.name)) {
            throw IllegalArgumentException("Category with name '${dto.name}' already exists")
        }
        
        existing.name = dto.name
        existing.description = dto.description
        existing.active = dto.active
        
        val updated = repository.save(existing)
        return updated.toDTO()
    }
    
    fun delete(id: Long) {
        if (!repository.existsById(id)) {
            throw NoSuchElementException("Category not found with id: $id")
        }
        repository.deleteById(id)
    }
    
    fun toggleActive(id: Long): CategoryDTO {
        val category = repository.findById(id)
            .orElseThrow { NoSuchElementException("Category not found with id: $id") }
        category.active = !category.active
        val updated = repository.save(category)
        return updated.toDTO()
    }
}
