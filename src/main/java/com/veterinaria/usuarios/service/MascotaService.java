package com.veterinaria.usuarios.service;

import com.veterinaria.usuarios.dto.MascotaDTO;

import java.util.List;
import java.util.Optional;

public interface MascotaService {
    List<MascotaDTO> findAll();
    Optional<MascotaDTO> findById(String id);
    List<MascotaDTO> findByPropietarioId(String propietarioId);
    MascotaDTO save(MascotaDTO mascotaDTO);
    MascotaDTO update(String id, MascotaDTO mascotaDTO);
    void deleteById(String id);
}