package com.veterinaria.usuarios.controller;

import com.veterinaria.usuarios.model.Disponibilidad;
import com.veterinaria.usuarios.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/veterinario/{veterinarioId}")
    public ResponseEntity<List<Disponibilidad>> getDisponibilidadesByVeterinarioId(@PathVariable String veterinarioId) {
        List<Disponibilidad> disponibilidades = disponibilidadService.findByVeterinarioId(veterinarioId);
        return ResponseEntity.ok(disponibilidades);
    }

    @GetMapping("/veterinario/{veterinarioId}/activas")
    public ResponseEntity<List<Disponibilidad>> getDisponibilidadesActivasByVeterinarioId(@PathVariable String veterinarioId) {
        List<Disponibilidad> disponibilidades = disponibilidadService.findByVeterinarioIdAndActivoTrue(veterinarioId);
        return ResponseEntity.ok(disponibilidades);
    }

    @GetMapping("/verificar/{veterinarioId}")
    public ResponseEntity<Map<String, Object>> verificarDisponibilidad(
            @PathVariable String veterinarioId,
            @RequestParam String fecha,
            @RequestParam String hora) {

        Map<String, Object> response = new HashMap<>();

        try {
            LocalDate fechaConsulta = LocalDate.parse(fecha);
            LocalTime horaConsulta = LocalTime.parse(hora);

            boolean disponible = disponibilidadService
                    .isVeterinarioDisponible(veterinarioId, fechaConsulta, horaConsulta);

            response.put("disponible", disponible);
            response.put("veterinarioId", veterinarioId);
            response.put("fecha", fecha);
            response.put("hora", hora);

            if (disponible) {
                response.put("mensaje", "El veterinario está disponible en el horario solicitado");
            } else {
                response.put("mensaje", "El veterinario NO está disponible en el horario solicitado");
            }

            return ResponseEntity.ok(response);

        } catch (DateTimeParseException e) {
            response.put("error", "Formato de fecha u hora inválido. Use formato ISO (yyyy-MM-dd para fecha y HH:mm para hora)");
            response.put("ejemplo", "fecha=2024-03-15&hora=14:30");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("error", "Error al verificar disponibilidad: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/veterinario/{veterinarioId}/dia")
    public ResponseEntity<Map<String, Object>> getDisponibilidadesPorDia(
            @PathVariable String veterinarioId,
            @RequestParam String fecha) {

        Map<String, Object> response = new HashMap<>();

        try {
            LocalDate fechaConsulta = LocalDate.parse(fecha);

            List<Disponibilidad> disponibilidades = disponibilidadService
                    .getDisponibilidadesPorDia(veterinarioId, fechaConsulta);

            response.put("veterinarioId", veterinarioId);
            response.put("fecha", fecha);
            response.put("diaSemana", fechaConsulta.getDayOfWeek().toString());
            response.put("disponibilidades", disponibilidades);
            response.put("totalHorarios", disponibilidades.size());

            return ResponseEntity.ok(response);

        } catch (DateTimeParseException e) {
            response.put("error", "Formato de fecha inválido. Use formato ISO (yyyy-MM-dd)");
            response.put("ejemplo", "fecha=2024-03-15");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("error", "Error al obtener disponibilidades: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
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