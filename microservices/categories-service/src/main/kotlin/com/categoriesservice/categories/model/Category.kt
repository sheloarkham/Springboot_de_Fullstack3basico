package com.categoriesservice.categories.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @field:NotBlank(message = "Name is required")
    @field:Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false, unique = true)
    var name: String = "",
    
    @field:Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(length = 500)
    var description: String? = null,
    
    @Column(nullable = false)
    var active: Boolean = true
)
