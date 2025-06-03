package com.veterinaria.usuarios.repository;

import com.veterinaria.usuarios.model.Veterinario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VeterinarioRepository extends MongoRepository<Veterinario, String> {
    Optional<Veterinario> findByEmail(String email);
    Optional<Veterinario> findByMatriculaProfesional(String matricula);
    boolean existsByEmail(String email);
    boolean existsByMatriculaProfesional(String matricula);
}