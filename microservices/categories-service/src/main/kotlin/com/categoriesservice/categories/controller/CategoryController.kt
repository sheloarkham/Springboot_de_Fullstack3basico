package com.categoriesservice.categories.controller

import com.categoriesservice.categories.dto.CategoryDTO
import com.categoriesservice.categories.service.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val service: CategoryService
) {
    
    @GetMapping
    fun getAllCategories(): ResponseEntity<List<CategoryDTO>> =
        ResponseEntity.ok(service.findAll())
    
    @GetMapping("/active")
    fun getActiveCategories(): ResponseEntity<List<CategoryDTO>> =
        ResponseEntity.ok(service.findAllActive())
    
    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<CategoryDTO> =
        ResponseEntity.ok(service.findById(id))
    
    @PostMapping
    fun createCategory(@Valid @RequestBody dto: CategoryDTO): ResponseEntity<CategoryDTO> {
        val created = service.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }
    
    @PutMapping("/{id}")
    fun updateCategory(
        @PathVariable id: Long,
        @Valid @RequestBody dto: CategoryDTO
    ): ResponseEntity<CategoryDTO> {
        val updated = service.update(id, dto)
        return ResponseEntity.ok(updated)
    }
    
    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
    
    @PatchMapping("/{id}/toggle-active")
    fun toggleActive(@PathVariable id: Long): ResponseEntity<CategoryDTO> {
        val updated = service.toggleActive(id)
        return ResponseEntity.ok(updated)
    }
}
