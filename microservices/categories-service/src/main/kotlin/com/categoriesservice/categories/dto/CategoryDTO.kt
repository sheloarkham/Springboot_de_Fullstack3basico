package com.categoriesservice.categories.dto

import com.categoriesservice.categories.model.Category
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CategoryDTO(
    val id: Long? = null,
    
    @field:NotBlank(message = "Name is required")
    @field:Size(min = 2, max = 100, message = "Nome must be between 2 and 100 characters")
    val name: String,
    
    @field:Size(max = 500, message = "Description cannot exceed 500 characters")
    val description: String? = null,
    
    val active: Boolean = true
)

// Extension functions para convertir entre Entity y DTO
fun Category.toDTO() = CategoryDTO(
    id = this.id,
    name = this.name,
    description = this.description,
    active = this.active
)

fun CategoryDTO.toEntity() = Category(
    id = this.id ?: 0,
    name = this.name,
    description = this.description,
    active = this.active
)
