package com.productsservice.products.service;

import com.productsservice.products.dto.ProductDTO;
import com.productsservice.products.model.Product;
import com.productsservice.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service - Capa de lógica de negocio
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Lista todos los productos
     */
    public List<ProductDTO> listarTodos() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Guarda un nuevo producto
     */
    public ProductDTO guardar(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    /**
     * Obtiene un producto por ID
     */
    public ProductDTO obtenerPorId(Long id) {
        return productRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    /**
     * Actualiza un producto completamente
     */
    public ProductDTO actualizar(Long id, ProductDTO productDTO) {
        return productRepository.findById(id)
                .map(productExistente -> {
                    productExistente.setName(productDTO.getName());
                    productExistente.setDescription(productDTO.getDescription());
                    productExistente.setPrice(productDTO.getPrice());
                    productExistente.setStock(productDTO.getStock());
                    productExistente.setCategory(productDTO.getCategory());
                    Product updated = productRepository.save(productExistente);
                    return convertToDTO(updated);
                })
                .orElse(null);
    }

    /**
     * Actualiza un producto parcialmente
     */
    public ProductDTO actualizarParcial(Long id, ProductDTO productDTO) {
        return productRepository.findById(id)
                .map(productExistente -> {
                    if (productDTO.getName() != null) {
                        productExistente.setName(productDTO.getName());
                    }
                    if (productDTO.getDescription() != null) {
                        productExistente.setDescription(productDTO.getDescription());
                    }
                    if (productDTO.getPrice() != null) {
                        productExistente.setPrice(productDTO.getPrice());
                    }
                    if (productDTO.getStock() != null) {
                        productExistente.setStock(productDTO.getStock());
                    }
                    if (productDTO.getCategory() != null) {
                        productExistente.setCategory(productDTO.getCategory());
                    }
                    Product updated = productRepository.save(productExistente);
                    return convertToDTO(updated);
                })
                .orElse(null);
    }

    /**
     * Elimina un producto
     */
    public boolean eliminar(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Convierte Entity a DTO
     */
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategory(product.getCategory());
        dto.setCreatedAt(product.getCreatedAt());
        return dto;
    }

    /**
     * Convierte DTO a Entity
     */
    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(dto.getCategory());
        return product;
    }
}
