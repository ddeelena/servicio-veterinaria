package com.veterinaria.usuarios.service;

import com.veterinaria.usuarios.dto.AuthResponse;
import com.veterinaria.usuarios.dto.LoginRequest;
import com.veterinaria.usuarios.dto.RegisterRequest;
import com.veterinaria.usuarios.dto.UserResponse;

/**
 * Interfaz para el servicio de autenticación de usuarios
 * Define los contratos para registro, login y gestión de perfiles de usuario
 */
public interface AuthService {

    /**
     * Registra un nuevo usuario en el sistema
     *
     * @param request datos del usuario a registrar
     * @return respuesta con token de autenticación y datos del usuario
     * @throws RuntimeException si el username o email ya existen
     */
    AuthResponse register(RegisterRequest request);

    /**
     * Autentica un usuario existente
     *
     * @param request credenciales de login
     * @return respuesta con token de autenticación y datos del usuario
     * @throws RuntimeException si las credenciales son inválidas
     */
    AuthResponse login(LoginRequest request);

    /**
     * Obtiene el perfil de un usuario por su username
     *
     * @param username nombre de usuario
     * @return datos del perfil del usuario
     * @throws RuntimeException si el usuario no existe
     */
    UserResponse getUserProfile(String username);
}