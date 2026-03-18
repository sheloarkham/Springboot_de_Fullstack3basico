package com.productsservice.products.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Model/Entity - Representacion de datos en la Base de Datos
 * 
 * Esta clase esta vinculada directamente a la tabla 'products' de la BD.
 * NO se debe exponer directamente en los endpoints HTTP.
 * En su lugar, usamos DTOs para transferir datos.
 * 
 * @Lombock @Data: Genera automaticamente getters, setters, equals(), toString()
 */
@Entity  
@Table(name = "products")
@Data
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    private String category;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
