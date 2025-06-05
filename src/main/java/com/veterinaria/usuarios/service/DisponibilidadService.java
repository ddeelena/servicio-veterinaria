package com.veterinaria.usuarios.service;

import com.veterinaria.usuarios.model.Disponibilidad;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface DisponibilidadService {

    List<Disponibilidad> findAll();

    Optional<Disponibilidad> findById(String id);

    List<Disponibilidad> findByVeterinarioId(String veterinarioId);

    List<Disponibilidad> findByVeterinarioIdAndActivoTrue(String veterinarioId);

    Disponibilidad save(Disponibilidad disponibilidad);

    Disponibilidad update(String id, Disponibilidad disponibilidad);

    void deleteById(String id);

    boolean existsById(String id);



    boolean isVeterinarioDisponible(String veterinarioId, LocalDate fecha, LocalTime hora);


    List<Disponibilidad> getDisponibilidadesPorDia(String veterinarioId, LocalDate fecha);
}