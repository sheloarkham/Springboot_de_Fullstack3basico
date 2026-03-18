package com.productsservice.products.controller;

import com.productsservice.products.dto.ProductDTO;
import com.productsservice.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller - API REST de Productos
 * 
 * Endpoints disponibles:
 * - GET    /api/v1/productos        - Lista todos los productos
 * - POST   /api/v1/productos        - Crea un nuevo producto
 * - GET    /api/v1/productos/{id}   - Obtiene un producto por ID
 * - PUT    /api/v1/productos/{id}   - Actualiza un producto completo
 * - PATCH  /api/v1/productos/{id}   - Actualiza parcialmente un producto
 * - DELETE /api/v1/productos/{id}   - Elimina un producto
 */
@RestController
@RequestMapping("/api/v1/productos")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * GET /productos
     * Retorna una lista de todos los productos en formato JSON
     */
    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.listarTodos();
    }

    /**
     * POST /productos
     * Permite agregar un nuevo producto
     * 
     * Body ejemplo:
     * {
     *   "name": "Laptop Dell",
     *   "description": "Laptop de alto rendimiento",
     *   "price": 1200.00,
     *   "stock": 10,
     *   "category": "Tecnología"
     * }
     */
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.guardar(productDTO));
    }

    /**
     * GET /productos/{id}
     * Obtiene un producto específico por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        ProductDTO product = productService.obtenerPorId(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * PUT /productos/{id}
     * Actualiza completamente un producto existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updated = productService.actualizar(id, productDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * PATCH /productos/{id}
     * Actualiza parcialmente un producto existente
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> partialUpdate(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updated = productService.actualizarParcial(id, productDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * DELETE /productos/{id}
     * Elimina un producto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (productService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
