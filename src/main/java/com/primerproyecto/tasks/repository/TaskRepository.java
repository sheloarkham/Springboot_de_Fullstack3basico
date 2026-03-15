package com.primerproyecto.tasks.repository;

import com.primerproyecto.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Task Repository - Interfaz para acceso a datos de tareas
 * 
 * Spring Data JPA genera automáticamente las implementaciones.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    /**
     * Busca todas las tareas de un usuario específico
     * SQL generado: SELECT * FROM tasks WHERE user_id = ?
     */
    List<Task> findByUserId(Long userId);
    
    /**
     * Busca tareas completadas de un usuario
     * SQL generado: SELECT * FROM tasks WHERE user_id = ? AND completed = ?
     */
    List<Task> findByUserIdAndCompleted(Long userId, Boolean completed);
    
    /**
     * Busca tareas por título (búsqueda parcial, case-insensitive)
     * SQL generado: SELECT * FROM tasks WHERE title LIKE %?%
     */
    List<Task> findByTitleContainingIgnoreCase(String title);
}
