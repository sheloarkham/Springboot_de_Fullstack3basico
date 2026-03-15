package com.tasksservice.tasks.service;

import com.tasksservice.tasks.dto.TaskDTO;
import com.tasksservice.tasks.model.Task;
import com.tasksservice.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Task Service - Lógica de negocio para gestión de tareas
 */
@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    public List<TaskDTO> listarTodas() {
        return taskRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public TaskDTO guardar(TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        if (task.getCompleted() == null) {
            task.setCompleted(Boolean.FALSE);
        }
        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
    }
    
    public Optional<TaskDTO> obtenerPorId(Long id) {
        return taskRepository.findById(id)
                .map(this::convertToDTO);
    }
    
    public List<TaskDTO> obtenerPorUsuario(Long userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<TaskDTO> buscarPorTitulo(String titulo) {
        return taskRepository.findByTitleContainingIgnoreCase(titulo).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<TaskDTO> actualizar(Long id, TaskDTO taskDTO) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(taskDTO.getTitle());
                    task.setDescription(taskDTO.getDescription());
                    task.setCompleted(taskDTO.getCompleted());
                    task.setDueDate(taskDTO.getDueDate());
                    task.setUserId(taskDTO.getUserId());
                    Task updated = taskRepository.save(task);
                    return convertToDTO(updated);
                });
    }
    
    public Optional<TaskDTO> toggleCompleted(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setCompleted(!task.getCompleted());
                    Task updated = taskRepository.save(task);
                    return convertToDTO(updated);
                });
    }
    
    public boolean eliminar(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
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
    
    private Task convertToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.getCompleted());
        task.setDueDate(dto.getDueDate());
        task.setUserId(dto.getUserId());
        return task;
    }
}
