package com.veterinaria.usuarios.repository;

import com.veterinaria.usuarios.model.Mascota;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends MongoRepository<Mascota, String> {
    List<Mascota> findByPropietarioId(String propietarioId);
}