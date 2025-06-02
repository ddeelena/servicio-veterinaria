package com.veterinaria.usuarios.service.impl;

import com.veterinaria.usuarios.dto.UsuarioDTO;
import com.veterinaria.usuarios.model.Propietario;
import com.veterinaria.usuarios.repository.UsuarioRepository;
import com.veterinaria.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioDTO> findById(String id) {
        return usuarioRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Propietario usuario = convertToEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return convertToDTO(usuario);
    }

    @Override
    public UsuarioDTO update(String id, UsuarioDTO usuarioDTO) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }

        Propietario usuario = convertToEntity(usuarioDTO);
        usuario.setId(id);
        usuario = usuarioRepository.save(usuario);
        return convertToDTO(usuario);
    }

    @Override
    public void deleteById(String id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public Optional<UsuarioDTO> findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(this::convertToDTO);
    }

    private UsuarioDTO convertToDTO(Propietario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setDireccion(usuario.getDireccion());
        return usuarioDTO;
    }

    private Propietario convertToEntity(UsuarioDTO usuarioDTO) {
        Propietario usuario = new Propietario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setDireccion(usuarioDTO.getDireccion());
        return usuario;
    }
}