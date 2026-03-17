package com.categoriesservice.categories.controller;

import com.categoriesservice.categories.dto.CategoryDTO;
import com.categoriesservice.categories.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Category Controller - REST API para gestión de categorías
 */
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listarTodas() {
        return ResponseEntity.ok(categoryService.listarTodas());
    }
    
    @PostMapping
    public ResponseEntity<CategoryDTO> crear(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO nuevaCategory = categoryService.guardar(categoryDTO);
        return ResponseEntity.ok(nuevaCategory);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> obtenerPorId(@PathVariable Long id) {
        return categoryService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CategoryDTO>> obtenerPorUsuario(@PathVariable Long userId) {
        return ResponseEntity.ok(categoryService.obtenerPorUsuario(userId));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<CategoryDTO>> buscarPorNombre(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.buscarPorNombre(name));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> actualizar(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.actualizar(id, categoryDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (categoryService.eliminar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
