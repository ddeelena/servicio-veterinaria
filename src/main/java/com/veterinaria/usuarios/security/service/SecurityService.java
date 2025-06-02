package com.veterinaria.usuarios.security.service;

import com.veterinaria.usuarios.model.Mascota;
import com.veterinaria.usuarios.model.Propietario;
import com.veterinaria.usuarios.repository.MascotaRepository;
import com.veterinaria.usuarios.repository.UsuarioRepository;
import com.veterinaria.usuarios.security.model.UserAuth;
import com.veterinaria.usuarios.security.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    // Verifica si el usuario actual es el dueño del recurso
    public boolean isOwner(String usuarioId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return false;
        }

        String username = auth.getName();
        Optional<Propietario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isEmpty()) {
            return false;
        }

        Optional<UserAuth> userAuth = userAuthRepository.findByUsername(username);
        if (userAuth.isEmpty()) {
            return false;
        }

        // Aquí deberías tener una forma de relacionar UserAuth con Usuario
        // Esta es una implementación simplificada, deberías adaptar esto a tu modelo de datos
        return usuario.get().getEmail().equals(username);
    }

    // Verifica si el usuario actual es dueño del email
    public boolean isEmailOwner(String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return false;
        }

        String username = auth.getName();
        // Asumiendo que el email es el mismo que el username del usuario autenticado
        return email.equals(username);
    }

    // Verifica si el usuario actual es dueño de la mascota
    public boolean isMascotaOwner(String mascotaId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return false;
        }

        String username = auth.getName();
        Optional<Mascota> mascota = mascotaRepository.findById(mascotaId);
        if (mascota.isEmpty()) {
            return false;
        }

        String propietarioId = mascota.get().getPropietarioId();
        if (propietarioId == null) {
            return false;
        }

        Optional<Propietario> propietario = usuarioRepository.findById(propietarioId);
        if (propietario.isEmpty()) {
            return false;
        }

        // Asumiendo que el email del propietario es el mismo que el username del usuario autenticado
        return propietario.get().getEmail().equals(username);
    }
}