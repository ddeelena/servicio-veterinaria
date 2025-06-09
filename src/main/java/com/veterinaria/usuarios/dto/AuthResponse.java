package com.veterinaria.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String role;
    private String username;
    private String userId;
    private String email;

    public AuthResponse(String token, String role, String username) {
        this.token = token;
        this.role = role;
        this.username = username;
    }
}
