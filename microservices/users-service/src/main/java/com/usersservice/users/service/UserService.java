package com.usersservice.users.service;

import com.usersservice.users.dto.UserDTO;
import com.usersservice.users.model.User;
import com.usersservice.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service - Capa de lógica de negocio
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> listarTodos() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO guardar(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

    public UserDTO obtenerPorId(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public UserDTO actualizar(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(userExistente -> {
                    userExistente.setUsername(userDTO.getUsername());
                    userExistente.setEmail(userDTO.getEmail());
                    userExistente.setFirstName(userDTO.getFirstName());
                    userExistente.setLastName(userDTO.getLastName());
                    userExistente.setPhone(userDTO.getPhone());
                    User updated = userRepository.save(userExistente);
                    return convertToDTO(updated);
                })
                .orElse(null);
    }

    public UserDTO actualizarParcial(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(userExistente -> {
                    // Solo actualiza los campos que vienen en el DTO
                    if (userDTO.getUsername() != null) {
                        userExistente.setUsername(userDTO.getUsername());
                    }
                    if (userDTO.getEmail() != null) {
                        userExistente.setEmail(userDTO.getEmail());
                    }
                    if (userDTO.getFirstName() != null) {
                        userExistente.setFirstName(userDTO.getFirstName());
                    }
                    if (userDTO.getLastName() != null) {
                        userExistente.setLastName(userDTO.getLastName());
                    }
                    if (userDTO.getPhone() != null) {
                        userExistente.setPhone(userDTO.getPhone());
                    }
                    User updated = userRepository.save(userExistente);
                    return convertToDTO(updated);
                })
                .orElse(null);
    }

    public boolean eliminar(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        return user;
    }
}
