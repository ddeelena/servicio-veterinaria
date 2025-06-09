package com.veterinaria.usuarios.service.impl;

import com.veterinaria.usuarios.config.JwtService;
import com.veterinaria.usuarios.dto.AuthResponse;
import com.veterinaria.usuarios.dto.LoginRequest;
import com.veterinaria.usuarios.dto.RegisterRequest;
import com.veterinaria.usuarios.dto.UserResponse;
import com.veterinaria.usuarios.model.User;
import com.veterinaria.usuarios.repository.UserRepository;
import com.veterinaria.usuarios.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Implementación del servicio de autenticación
 * Maneja el registro, login y gestión de perfiles de usuario
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthResponse register(RegisterRequest request) {
        log.info("Iniciando registro de usuario: {}", request.getUsername());

        validateUserNotExists(request);

        User user = buildUserFromRequest(request);
        User savedUser = userRepository.save(user);

        log.info("Usuario registrado exitosamente: {}", savedUser.getUsername());

        String token = jwtService.generateToken(savedUser);
        return buildAuthResponse(savedUser, token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthResponse login(LoginRequest request) {
        log.info("Iniciando autenticación para usuario: {}", request.getUsername());

        authenticateUser(request);

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        updateLastConnection(user);

        log.info("Usuario autenticado exitosamente: {}", user.getUsername());

        String token = jwtService.generateToken(user);
        return buildAuthResponse(user, token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserProfile(String username) {
        log.info("Obteniendo perfil de usuario: {}", username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return buildUserResponse(user);
    }

    // Métodos privados de utilidad

    private void validateUserNotExists(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            log.warn("Intento de registro con username existente: {}", request.getUsername());
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            log.warn("Intento de registro con email existente: {}", request.getEmail());
            throw new RuntimeException("El email ya está registrado");
        }
    }

    private User buildUserFromRequest(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    private void authenticateUser(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
    }

    private void updateLastConnection(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    private AuthResponse buildAuthResponse(User user, String token) {
        return new AuthResponse(
                token,
                "ROLE_" + user.getRole().name(),
                user.getUsername(),
                user.getId(),
                user.getEmail()
        );
    }

    private UserResponse buildUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        response.setEnabled(user.isEnabled());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}