package com.veterinaria.usuarios.controller;

import com.veterinaria.usuarios.dto.MascotaDTO;
import com.veterinaria.usuarios.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    @Autowired
    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> findAll() {
        return ResponseEntity.ok(mascotaService.findAll());
    }

    @GetMapping("/{id}")

    public ResponseEntity<MascotaDTO> findById(@PathVariable String id) {
        return mascotaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/propietario/{propietarioId}")
    public ResponseEntity<List<MascotaDTO>> findByPropietarioId(@PathVariable String propietarioId) {
        return ResponseEntity.ok(mascotaService.findByPropietarioId(propietarioId));
    }

    @PostMapping
    public ResponseEntity<MascotaDTO> save(@Valid @RequestBody MascotaDTO mascotaDTO) {
        if (mascotaDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            MascotaDTO saved = mascotaService.save(mascotaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaDTO> update(@PathVariable String id, @Valid @RequestBody MascotaDTO mascotaDTO) {
        try {
            MascotaDTO updated = mascotaService.update(id, mascotaDTO);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        try {
            mascotaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}