package com.tasksservice.tasks.controller;

import com.tasksservice.tasks.dto.TaskDTO;
import com.tasksservice.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Task Controller - REST API para gestión de tareas
 */
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @GetMapping
    public ResponseEntity<List<TaskDTO>> listarTodas() {
        return ResponseEntity.ok(taskService.listarTodas());
    }
    
    @PostMapping
    public ResponseEntity<TaskDTO> crear(@RequestBody TaskDTO taskDTO) {
        TaskDTO nuevaTask = taskService.guardar(taskDTO);
        return ResponseEntity.ok(nuevaTask);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> obtenerPorId(@PathVariable Long id) {
        return taskService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskDTO>> obtenerPorUsuario(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.obtenerPorUsuario(userId));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<TaskDTO>> buscarPorTitulo(@RequestParam String title) {
        return ResponseEntity.ok(taskService.buscarPorTitulo(title));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> actualizar(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return taskService.actualizar(id, taskDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TaskDTO> toggleCompleted(@PathVariable Long id) {
        return taskService.toggleCompleted(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (taskService.eliminar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
