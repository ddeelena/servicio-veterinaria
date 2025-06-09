package com.veterinaria.usuarios.service.impl;

import com.veterinaria.usuarios.model.User;
import com.veterinaria.usuarios.repository.UserRepository;
import com.veterinaria.usuarios.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio personalizado de detalles de usuario
 * Proporciona funcionalidad para cargar usuarios desde la base de datos
 * para la autenticación y autorización de Spring Security
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Cargando usuario por username: {}", username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("Usuario no encontrado: {}", username);
                    return new UsernameNotFoundException("Usuario no encontrado: " + username);
                });

        log.debug("Usuario cargado exitosamente: {}", username);
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean userExists(String username) {
        log.debug("Verificando existencia de usuario: {}", username);

        boolean exists = userRepository.existsByUsername(username);

        log.debug("Usuario {} {}", username, exists ? "existe" : "no existe");
        return exists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        log.debug("Cargando usuario por email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Usuario no encontrado con email: {}", email);
                    return new UsernameNotFoundException("Usuario no encontrado con email: " + email);
                });

        log.debug("Usuario cargado exitosamente por email: {}", email);
        return user;
    }
}