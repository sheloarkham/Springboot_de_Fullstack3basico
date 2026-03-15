package com.primerproyecto.tasks.controller;

import com.primerproyecto.tasks.dto.TaskDTO;
import com.primerproyecto.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Task Controller - API REST para gestión de tareas
 * 
 * Endpoints disponibles:
 * - GET    /api/v1/tasks              → Listar todas las tareas
 * - GET    /api/v1/tasks/{id}         → Obtener tarea por ID
 * - GET    /api/v1/tasks/user/{userId}→ Listar tareas de un usuario
 * - GET    /api/v1/tasks/search?title=→ Buscar tareas por título
 * - POST   /api/v1/tasks              → Crear nueva tarea
 * - PUT    /api/v1/tasks/{id}         → Actualizar tarea
 * - PATCH  /api/v1/tasks/{id}/toggle  → Marcar como completada/pendiente
 * - DELETE /api/v1/tasks/{id}         → Eliminar tarea
 */
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    /**
     * GET /api/v1/tasks
     * Lista todas las tareas del sistema
     */
    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.listarTodas();
    }
    
    /**
     * GET /api/v1/tasks/{id}
     * Obtiene una tarea específica por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        TaskDTO task = taskService.obtenerPorId(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * GET /api/v1/tasks/user/{userId}
     * Lista todas las tareas de un usuario específico
     * Ejemplo: GET /api/v1/tasks/user/1
     */
    @GetMapping("/user/{userId}")
    public List<TaskDTO> getTasksByUser(@PathVariable Long userId) {
        return taskService.listarPorUsuario(userId);
    }
    
    /**
     * GET /api/v1/tasks/search?title=compras
     * Busca tareas por título (búsqueda parcial)
     */
    @GetMapping("/search")
    public List<TaskDTO> searchTasks(@RequestParam String title) {
        return taskService.buscarPorTitulo(title);
    }
    
    /**
     * POST /api/v1/tasks
     * Crea una nueva tarea
     * 
     * Body ejemplo:
     * {
     *   "title": "Comprar leche",
     *   "description": "Ir al supermercado",
     *   "completed": false,
     *   "dueDate": "2026-03-20",
     *   "userId": 1
     * }
     */
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO created = taskService.crear(taskDTO);
        return ResponseEntity.ok(created);
    }
    
    /**
     * PUT /api/v1/tasks/{id}
     * Actualiza una tarea completa
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable Long id,
            @RequestBody TaskDTO taskDTO) {
        TaskDTO updated = taskService.actualizar(id, taskDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * PATCH /api/v1/tasks/{id}/toggle
     * Alterna el estado de completitud (completada ↔ pendiente)
     * No requiere body, solo el ID en la URL
     */
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TaskDTO> toggleTaskCompleted(@PathVariable Long id) {
        TaskDTO toggled = taskService.toggleCompleted(id);
        if (toggled != null) {
            return ResponseEntity.ok(toggled);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * DELETE /api/v1/tasks/{id}
     * Elimina una tarea por su ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.eliminar(id)) {
            return ResponseEntity.noContent().build();  // 204
        }
        return ResponseEntity.notFound().build();  // 404
    }
}
