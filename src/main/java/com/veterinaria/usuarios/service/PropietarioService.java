package com.veterinaria.usuarios.service;

import com.veterinaria.usuarios.dto.PropietarioDTO;
import com.veterinaria.usuarios.model.Propietario;

import java.util.List;
import java.util.Optional;

public interface PropietarioService {
    List<PropietarioDTO> findAll();
    Optional<PropietarioDTO> findById(String id);
    PropietarioDTO save(PropietarioDTO propietarioDTO);
    PropietarioDTO update(String id, PropietarioDTO propietarioDTO);
    void deleteById(String id);
    boolean existsByEmail(String email);
    Optional<PropietarioDTO> findByEmail(String email);

    Propietario crearPropietario(Propietario propietario);

    List<Propietario> obtenerTodosLosPropietarios();

    Optional<Propietario> obtenerPropietarioPorId(String id);

    List<Propietario> buscarPorNombre(String nombre);

    Optional<Propietario> buscarPorEmail(String email);

    Optional<Propietario> buscarPorTelefono(String telefono);

    Propietario actualizarPropietario(String id, Propietario propietario);

    Propietario actualizarPropietarioParcial(String id, Propietario propietarioParcial);

    boolean eliminarPropietario(String id);

    long contarPropietarios();

    boolean existePorEmail(String email);

    boolean existePorTelefono(String telefono);
}