package com.veterinaria.usuarios.controller;

import com.veterinaria.usuarios.model.Disponibilidad;
import com.veterinaria.usuarios.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/disponibilidades")
@CrossOrigin(origins = "*")
public class DisponibilidadController {

    @Autowired
    private DisponibilidadService disponibilidadService;

    @GetMapping
    public List<Disponibilidad> getAllDisponibilidades() {
        return disponibilidadService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disponibilidad> getDisponibilidadById(@PathVariable String id) {
        return disponibilidadService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Disponibilidad> createDisponibilidad(@Valid @RequestBody Disponibilidad disponibilidad) {
        Disponibilidad saved = disponibilidadService.save(disponibilidad);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disponibilidad> updateDisponibilidad(
            @PathVariable String id,
            @Valid @RequestBody Disponibilidad disponibilidad) {
        Disponibilidad updated = disponibilidadService.update(id, disponibilidad);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilidad(@PathVariable String id) {
        disponibilidadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}