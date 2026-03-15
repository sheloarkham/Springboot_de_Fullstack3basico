package com.tasksservice.tasks.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

/**
 * Task Entity - Representa una tarea en la base de datos
 */
@Entity
@Table(name = "tasks")
@Data
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(length = 500)
    private String description;
    
    @Column(nullable = false)
    private Boolean completed = false;
    
    private LocalDate dueDate;
    
    @Column(nullable = false)
    private Long userId;
}
