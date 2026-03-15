package com.tasksservice.tasks.dto;

import lombok.Data;
import java.time.LocalDate;

/**
 * Task Data Transfer Object
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
