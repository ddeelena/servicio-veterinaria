package com.veterinaria.usuarios.controller;

import com.veterinaria.usuarios.model.Veterinario;
import com.veterinaria.usuarios.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
@CrossOrigin(origins = "*")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping
    public List<Veterinario> getAllVeterinarios() {
        return veterinarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> getVeterinarioById(@PathVariable String id) {
        return veterinarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createVeterinario(@Valid @RequestBody Veterinario veterinario) {
        if (veterinarioService.existsByEmail(veterinario.getEmail())) {
            return ResponseEntity.badRequest()
                    .body("Error: El email ya está registrado");
        }

        if (veterinarioService.existsByMatricula(veterinario.getMatriculaProfesional())) {
            return ResponseEntity.badRequest()
                    .body("Error: La matrícula profesional ya está registrada");
        }

        Veterinario saved = veterinarioService.save(veterinario);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veterinario> updateVeterinario(
            @PathVariable String id,
            @Valid @RequestBody Veterinario veterinario) {
        Veterinario updated = veterinarioService.update(id, veterinario);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinario(@PathVariable String id) {
        veterinarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}