package com.veterinaria.usuarios.service;

import com.veterinaria.usuarios.model.Veterinario;
import java.util.List;
import java.util.Optional;

public interface VeterinarioService {
    List<Veterinario> findAll();
    Optional<Veterinario> findById(String id);
    Optional<Veterinario> findByEmail(String email);
    Optional<Veterinario> findByMatriculaProfesional(String matricula);
    Veterinario save(Veterinario veterinario);
    Veterinario update(String id, Veterinario veterinario);
    void deleteById(String id);
    boolean existsByEmail(String email);
    boolean existsByMatricula(String matricula);
}