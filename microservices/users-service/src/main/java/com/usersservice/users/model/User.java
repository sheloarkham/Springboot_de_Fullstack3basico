package com.usersservice.users.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Model/Entity - Representacion de datos en la Base de Datos
 * 
 * Esta clase esta vinculada directamente a la tabla 'users' de la BD.
 * NO se debe exponer directamente en los endpoints HTTP.
 * En su lugar, usamos DTOs para transferir datos.
 * 
 * @Lombock @Data: Genera automaticamente getters, setters, equals(), toString()
 */
@Entity  
@Table(name = "users")
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;
}
