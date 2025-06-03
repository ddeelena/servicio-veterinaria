package com.veterinaria.usuarios.service.impl;

import com.veterinaria.usuarios.model.Disponibilidad;
import com.veterinaria.usuarios.repository.DisponibilidadRepository;
import com.veterinaria.usuarios.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}