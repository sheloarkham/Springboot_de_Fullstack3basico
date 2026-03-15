package com.primerproyecto.tasks.service;

import com.primerproyecto.tasks.dto.TaskDTO;
import com.primerproyecto.tasks.model.Task;
import com.primerproyecto.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Task Service - Lógica de negocio para tareas
 * 
 * Maneja las operaciones CRUD y conversiones DTO ↔ Entity
 */
@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    /**
     * Obtiene todas las tareas
     */
    public List<TaskDTO> listarTodas() {
        return taskRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtiene todas las tareas de un usuario específico
     */
    public List<TaskDTO> listarPorUsuario(Long userId) {
        return taskRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtiene una tarea por su ID
     */
    public TaskDTO obtenerPorId(Long id) {
        return taskRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }
    
    /**
     * Crea una nueva tarea
     */
    public TaskDTO crear(TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
    }
    
    /**
     * Actualiza una tarea existente
     */
    public TaskDTO actualizar(Long id, TaskDTO taskDTO) {
        return taskRepository.findById(id)
                .map(taskExistente -> {
                    taskExistente.setTitle(taskDTO.getTitle());
                    taskExistente.setDescription(taskDTO.getDescription());
                    taskExistente.setCompleted(taskDTO.getCompleted());
                    taskExistente.setDueDate(taskDTO.getDueDate());
                    taskExistente.setUserId(taskDTO.getUserId());
                    Task updated = taskRepository.save(taskExistente);
                    return convertToDTO(updated);
                })
                .orElse(null);
    }
    
    /**
     * Marca una tarea como completada/no completada
     */
    public TaskDTO toggleCompleted(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setCompleted(!task.getCompleted());
                    Task updated = taskRepository.save(task);
                    return convertToDTO(updated);
                })
                .orElse(null);
    }
    
    /**
     * Elimina una tarea
     */
    public boolean eliminar(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    /**
     * Busca tareas por título
     */
    public List<TaskDTO> buscarPorTitulo(String titulo) {
        return taskRepository.findByTitleContainingIgnoreCase(titulo)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Conversión: Entity → DTO
     */
    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.getCompleted());
        dto.setDueDate(task.getDueDate());
        dto.setUserId(task.getUserId());
        return dto;
    }
    
    /**
     * Conversión: DTO → Entity
     */
    private Task convertToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.getCompleted() != null ? dto.getCompleted() : Boolean.FALSE);
        task.setDueDate(dto.getDueDate());
        task.setUserId(dto.getUserId());
        return task;
    }
}
