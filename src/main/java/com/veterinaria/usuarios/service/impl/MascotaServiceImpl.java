package com.veterinaria.usuarios.service.impl;

import com.veterinaria.usuarios.dto.MascotaDTO;
import com.veterinaria.usuarios.model.Mascota;
import com.veterinaria.usuarios.model.Propietario;
import com.veterinaria.usuarios.repository.MascotaRepository;
import com.veterinaria.usuarios.repository.PropietarioRepository;
import com.veterinaria.usuarios.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;

    @Autowired
    public MascotaServiceImpl(MascotaRepository mascotaRepository, PropietarioRepository propietarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.propietarioRepository = propietarioRepository;
    }

    @Override
    public List<MascotaDTO> findAll() {
        return mascotaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MascotaDTO> findById(String id) {
        return mascotaRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<MascotaDTO> findByPropietarioId(String propietarioId) {
        if (!propietarioRepository.existsById(propietarioId)) {
            throw new RuntimeException("Propietario no encontrado con ID: " + propietarioId);
        }

        return mascotaRepository.findByPropietarioId(propietarioId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaDTO save(MascotaDTO mascotaDTO) {
        String propietarioId = mascotaDTO.getPropietarioId();
        Propietario propietario = propietarioRepository.findById(propietarioId)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado con ID: " + propietarioId));

        Mascota mascota = convertToEntity(mascotaDTO);
        mascota = mascotaRepository.save(mascota);

        // Actualizar la relación en el usuario
        propietario.addMascota(mascota);
        propietarioRepository.save(propietario);

        return convertToDTO(mascota);
    }

    @Override
    public MascotaDTO update(String id, MascotaDTO mascotaDTO) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada con ID: " + id);
        }

        Mascota mascota = convertToEntity(mascotaDTO);
        mascota.setId(id);
        mascota = mascotaRepository.save(mascota);
        return convertToDTO(mascota);
    }

    @Override
    public void deleteById(String id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada con ID: " + id);
        }

        // Recuperar la mascota y el propietario para actualizar la relación
        Mascota mascota = mascotaRepository.findById(id).orElseThrow();
        String propietarioId = mascota.getPropietarioId();

        Optional<Propietario> propietarioOpt = propietarioRepository.findById(propietarioId);
        if (propietarioOpt.isPresent()) {
            Propietario propietario = propietarioOpt.get();
            propietario.removeMascota(mascota);
            propietarioRepository.save(propietario);
        }

        mascotaRepository.deleteById(id);
    }

    private MascotaDTO convertToDTO(Mascota mascota) {
        MascotaDTO mascotaDTO = new MascotaDTO();
        mascotaDTO.setId(mascota.getId());
        mascotaDTO.setNombre(mascota.getNombre());
        mascotaDTO.setEspecie(mascota.getEspecie());
        mascotaDTO.setRaza(mascota.getRaza());
        mascotaDTO.setEdad(mascota.getEdad());
        mascotaDTO.setPeso(mascota.getPeso());
        mascotaDTO.setObservaciones(mascota.getObservaciones());
        mascotaDTO.setPropietarioId(mascota.getPropietarioId());
        return mascotaDTO;
    }

    private Mascota convertToEntity(MascotaDTO mascotaDTO) {
        Mascota mascota = new Mascota();
        mascota.setId(mascotaDTO.getId());
        mascota.setNombre(mascotaDTO.getNombre());
        mascota.setEspecie(mascotaDTO.getEspecie());
        mascota.setRaza(mascotaDTO.getRaza());
        mascota.setEdad(mascotaDTO.getEdad());
        mascota.setPeso(mascotaDTO.getPeso());
        mascota.setObservaciones(mascotaDTO.getObservaciones());
        mascota.setPropietarioId(mascotaDTO.getPropietarioId());

        return mascota;
    }
}