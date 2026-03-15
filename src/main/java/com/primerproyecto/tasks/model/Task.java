package com.primerproyecto.tasks.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

/**
 * Task Entity - Representa una tarea en la base de datos
 * 
 * Esta entidad se mapea a la tabla 'tasks' en la BD.
 * Cada tarea pertenece a un usuario (userId).
 */
@Entity
@Table(name = "tasks")
@Data
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Título de la tarea - Obligatorio
     */
    @Column(nullable = false)
    private String title;
    
    /**
     * Descripción detallada de la tarea - Opcional
     */
    @Column(length = 500)
    private String description;
    
    /**
     * Estado de completitud de la tarea
     * Por defecto: false (no completada)
     */
    @Column(nullable = false)
    private Boolean completed = false;
    
    /**
     * Fecha límite para completar la tarea - Opcional
     */
    private LocalDate dueDate;
    
    /**
     * ID del usuario dueño de esta tarea
     * Relación con la tabla users (simple foreign key)
     */
    @Column(nullable = false)
    private Long userId;
}
