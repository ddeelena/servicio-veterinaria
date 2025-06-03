package com.veterinaria.usuarios.repository;

import com.veterinaria.usuarios.model.Propietario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropietarioRepository extends MongoRepository<Propietario, String> {
    Optional<Propietario> findByEmail(String email);
    boolean existsByEmail(String email);
}