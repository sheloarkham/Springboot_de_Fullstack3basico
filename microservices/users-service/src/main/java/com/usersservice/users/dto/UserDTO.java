package com.usersservice.users.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) para transferir datos de usuario entre capas.
 */
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
}
