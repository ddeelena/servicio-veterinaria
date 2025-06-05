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

    /**
     * Verifica si un veterinario está disponible en una fecha y hora específicas
     *
     * @param veterinarioId ID del veterinario
     * @param fecha Fecha de la consulta
     * @param hora Hora de la consulta
     * @return true si el veterinario está disponible, false en caso contrario
     */
    boolean isVeterinarioDisponible(String veterinarioId, LocalDate fecha, LocalTime hora);

    /**
     * Obtiene todas las disponibilidades de un veterinario para un día específico
     *
     * @param veterinarioId ID del veterinario
     * @param fecha Fecha para consultar las disponibilidades
     * @return Lista de disponibilidades para ese día
     */
    List<Disponibilidad> getDisponibilidadesPorDia(String veterinarioId, LocalDate fecha);
}