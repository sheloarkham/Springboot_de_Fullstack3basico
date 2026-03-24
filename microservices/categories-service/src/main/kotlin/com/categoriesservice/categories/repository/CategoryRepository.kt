package com.categoriesservice.categories.repository

import com.categoriesservice.categories.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByActiveTrue(): List<Category>
    fun existsByName(name: String): Boolean
}
