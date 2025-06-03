package com.veterinaria.usuarios.service.impl;

import com.veterinaria.usuarios.dto.PropietarioDTO;
import com.veterinaria.usuarios.model.Propietario;
import com.veterinaria.usuarios.repository.PropietarioRepository;
import com.veterinaria.usuarios.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropietarioServiceImpl implements PropietarioService {

    private final PropietarioRepository propietarioRepository;

    @Autowired
    public PropietarioServiceImpl(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    @Override
    public List<PropietarioDTO> findAll() {
        return propietarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PropietarioDTO> findById(String id) {
        return propietarioRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public PropietarioDTO save(PropietarioDTO propietarioDTO) {
        Propietario propietario = convertToEntity(propietarioDTO);
        propietario = propietarioRepository.save(propietario);
        return convertToDTO(propietario);
    }

    @Override
    public PropietarioDTO update(String id, PropietarioDTO propietarioDTO) {
        if (!propietarioRepository.existsById(id)) {
            throw new RuntimeException("Propietario no encontrado con ID: " + id);
        }

        Propietario propietario = convertToEntity(propietarioDTO);
        propietario.setId(id);
        propietario = propietarioRepository.save(propietario);
        return convertToDTO(propietario);
    }

    @Override
    public void deleteById(String id) {
        if (!propietarioRepository.existsById(id)) {
            throw new RuntimeException("Propietario no encontrado con ID: " + id);
        }
        propietarioRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return propietarioRepository.existsByEmail(email);
    }

    @Override
    public Optional<PropietarioDTO> findByEmail(String email) {
        return propietarioRepository.findByEmail(email)
                .map(this::convertToDTO);
    }

    @Override
    public Propietario crearPropietario(Propietario propietario) {
        return null;
    }

    @Override
    public List<Propietario> obtenerTodosLosPropietarios() {
        return null;
    }

    @Override
    public Optional<Propietario> obtenerPropietarioPorId(String id) {
        return Optional.empty();
    }

    @Override
    public List<Propietario> buscarPorNombre(String nombre) {
        return null;
    }

    @Override
    public Optional<Propietario> buscarPorEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Propietario> buscarPorTelefono(String telefono) {
        return Optional.empty();
    }

    @Override
    public Propietario actualizarPropietario(String id, Propietario propietario) {
        return null;
    }

    @Override
    public Propietario actualizarPropietarioParcial(String id, Propietario propietarioParcial) {
        return null;
    }

    @Override
    public boolean eliminarPropietario(String id) {
        return false;
    }

    @Override
    public long contarPropietarios() {
        return 0;
    }

    @Override
    public boolean existePorEmail(String email) {
        return false;
    }

    @Override
    public boolean existePorTelefono(String telefono) {
        return false;
    }

    private PropietarioDTO convertToDTO(Propietario propietario) {
        PropietarioDTO propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(propietario.getId());
        propietarioDTO.setNombre(propietario.getNombre());
        propietarioDTO.setApellido(propietario.getApellido());
        propietarioDTO.setEmail(propietario.getEmail());
        propietarioDTO.setTelefono(propietario.getTelefono());
        propietarioDTO.setDireccion(propietario.getDireccion());
        return propietarioDTO;
    }

    private Propietario convertToEntity(PropietarioDTO propietarioDTO) {
        Propietario propietario = new Propietario();
        propietario.setId(propietarioDTO.getId());
        propietario.setNombre(propietarioDTO.getNombre());
        propietario.setApellido(propietarioDTO.getApellido());
        propietario.setEmail(propietarioDTO.getEmail());
        propietario.setTelefono(propietarioDTO.getTelefono());
        propietario.setDireccion(propietarioDTO.getDireccion());
        return propietario;
    }
}
