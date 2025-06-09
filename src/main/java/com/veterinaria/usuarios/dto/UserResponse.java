package com.veterinaria.usuarios.dto;

import com.veterinaria.usuarios.model.Role;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private boolean enabled;
    private LocalDateTime createdAt;
}