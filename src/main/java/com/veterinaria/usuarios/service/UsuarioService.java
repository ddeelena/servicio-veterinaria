package com.veterinaria.usuarios.service;

import com.veterinaria.usuarios.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDTO> findAll();
    Optional<UsuarioDTO> findById(String id);
    UsuarioDTO save(UsuarioDTO usuarioDTO);
    UsuarioDTO update(String id, UsuarioDTO usuarioDTO);
    void deleteById(String id);
    boolean existsByEmail(String email);
    Optional<UsuarioDTO> findByEmail(String email);
}