package com.veterinaria.usuarios.service.impl;

import com.veterinaria.usuarios.model.Disponibilidad;
import com.veterinaria.usuarios.repository.DisponibilidadRepository;
import com.veterinaria.usuarios.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
public class DisponibilidadServiceImpl implements DisponibilidadService {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @Override
    public List<Disponibilidad> findAll() {
        return disponibilidadRepository.findAll();
    }

    @Override
    public Optional<Disponibilidad> findById(String id) {
        return disponibilidadRepository.findById(id);
    }

    @Override
    public List<Disponibilidad> findByVeterinarioId(String veterinarioId) {
        return disponibilidadRepository.findByVeterinarioId(veterinarioId);
    }

    @Override
    public List<Disponibilidad> findByVeterinarioIdAndActivoTrue(String veterinarioId) {
        return disponibilidadRepository.findByVeterinarioIdAndActivoTrue(veterinarioId);
    }

    @Override
    public Disponibilidad save(Disponibilidad disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }

    @Override
    public Disponibilidad update(String id, Disponibilidad disponibilidad) {
        if (disponibilidadRepository.existsById(id)) {
            disponibilidad.setId(id);
            return disponibilidadRepository.save(disponibilidad);
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        disponibilidadRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return disponibilidadRepository.existsById(id);
    }

    @Override
    public boolean isVeterinarioDisponible(String veterinarioId, LocalDate fecha, LocalTime hora) {
        try {
            DayOfWeek diaSemana = fecha.getDayOfWeek();

            List<Disponibilidad> disponibilidades = disponibilidadRepository
                    .findByVeterinarioIdAndDiaSemanaAndActivoTrue(veterinarioId, diaSemana.getValue());

            if (disponibilidades.isEmpty()) {
                return false;
            }

            for (Disponibilidad disponibilidad : disponibilidades) {
                LocalTime horaInicio = disponibilidad.getHoraInicio();
                LocalTime horaFin = disponibilidad.getHoraFin();

                if (!hora.isBefore(horaInicio) && hora.isBefore(horaFin)) {

                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Disponibilidad> getDisponibilidadesPorDia(String veterinarioId, LocalDate fecha) {
        DayOfWeek diaSemana = fecha.getDayOfWeek();
        return disponibilidadRepository
                .findByVeterinarioIdAndDiaSemanaAndActivoTrue(veterinarioId, diaSemana.getValue());
    }
}