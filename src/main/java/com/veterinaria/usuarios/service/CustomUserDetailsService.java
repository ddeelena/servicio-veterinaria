package com.veterinaria.usuarios.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Interfaz personalizada para el servicio de detalles de usuario
 * Extiende UserDetailsService de Spring Security para proporcionar
 * funcionalidad personalizada de carga de usuarios
 */
public interface CustomUserDetailsService extends UserDetailsService {

    /**
     * Carga los detalles de un usuario por su nombre de usuario
     *
     * @param username el nombre de usuario del usuario a cargar
     * @return los detalles del usuario como UserDetails
     * @throws UsernameNotFoundException si el usuario no se encuentra
     */
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Verifica si un usuario existe en el sistema
     *
     * @param username el nombre de usuario a verificar
     * @return true si el usuario existe, false en caso contrario
     */
    boolean userExists(String username);

    /**
     * Carga los detalles de un usuario por su email
     *
     * @param email el email del usuario a cargar
     * @return los detalles del usuario como UserDetails
     * @throws UsernameNotFoundException si el usuario no se encuentra
     */
    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
}