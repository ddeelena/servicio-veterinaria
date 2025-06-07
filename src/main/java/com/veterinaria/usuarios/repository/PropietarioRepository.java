package com.veterinaria.usuarios.repository;

import com.veterinaria.usuarios.model.Propietario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropietarioRepository extends MongoRepository<Propietario, String> {
    Optional<Propietario> findByEmail(String email);
    boolean existsByEmail(String email);


    @Query(value = "{ 'mascotas.id': ?0 }", fields = "{ '_id': 1 }")
    String findIdByMascotasId(String mascotaId);

    @Query(value = "{ 'mascotas.id': ?0 }", fields = "{ '_id': 1 }")
    String findEmailByMascotaId(String mascotaId);


    @Query(value = "{ 'mascotas.id': ?0 }", fields = "{ 'email': 1 }")
    Optional<Propietario> findByMascotaId(String mascotaId);
}