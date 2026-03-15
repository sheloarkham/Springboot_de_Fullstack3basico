package com.primerproyecto.tasks.dto;

import lombok.Data;
import java.time.LocalDate;

/**
 * Task Data Transfer Object
 * 
 * Objeto usado para transferir datos de tareas entre el cliente y el servidor.
 * Protege la entidad Task de ser expuesta directamente.
 */
@Data
public class TaskDTO {
    
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private LocalDate dueDate;
    private Long userId;
}
