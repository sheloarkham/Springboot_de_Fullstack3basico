package com.tasksservice.tasks.repository;

import com.tasksservice.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Task Repository - Interfaz para acceso a datos de tareas
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByUserId(Long userId);
    List<Task> findByUserIdAndCompleted(Long userId, Boolean completed);
    List<Task> findByTitleContainingIgnoreCase(String title);
}
