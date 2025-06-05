package com.veterinaria.usuarios.repository;

import com.veterinaria.usuarios.model.Disponibilidad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DisponibilidadRepository extends MongoRepository<Disponibilidad, String> {
    List<Disponibilidad> findByVeterinarioId(String veterinarioId);
    List<Disponibilidad> findByVeterinarioIdAndActivoTrue(String veterinarioId);
    List<Disponibilidad> findByVeterinarioIdAndDiaSemanaAndActivoTrue(String veterinarioId, Integer diaSemana);

}