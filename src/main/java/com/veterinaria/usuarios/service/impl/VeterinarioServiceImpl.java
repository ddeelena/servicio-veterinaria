package com.veterinaria.usuarios.service.impl;

import com.veterinaria.usuarios.model.Veterinario;
import com.veterinaria.usuarios.repository.VeterinarioRepository;
import com.veterinaria.usuarios.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Override
    public List<Veterinario> findAll() {
        return veterinarioRepository.findAll();
    }

    @Override
    public Optional<Veterinario> findById(String id) {
        return veterinarioRepository.findById(id);
    }

    @Override
    public Optional<Veterinario> findByEmail(String email) {
        return veterinarioRepository.findByEmail(email);
    }

    @Override
    public Optional<Veterinario> findByMatriculaProfesional(String matricula) {
        return veterinarioRepository.findByMatriculaProfesional(matricula);
    }

    @Override
    public Veterinario save(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Override
    public Veterinario update(String id, Veterinario veterinario) {
        if (veterinarioRepository.existsById(id)) {
            veterinario.setId(id);
            return veterinarioRepository.save(veterinario);
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        veterinarioRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return veterinarioRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByMatricula(String matricula) {
        return veterinarioRepository.existsByMatriculaProfesional(matricula);
    }
}