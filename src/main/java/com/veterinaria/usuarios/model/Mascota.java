package com.veterinaria.usuarios.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "mascotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    private String id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La especie es obligatoria")
    private String especie;

    private String raza;

    @NotNull(message = "La edad es obligatoria")
    @Positive(message = "La edad debe ser positiva")
    private Integer edad;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser positivo")
    private Double peso;

    private String observaciones;

    private String propietarioId;

    // Array de historial médico
    private List<HistorialMedico> historialMedico = new ArrayList<>();

    // Clase interna para el historial médico
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HistorialMedico {
        private String id;
        private LocalDateTime fecha;
        private String diagnostico;
        private String tratamiento;
        private String observaciones;
        private Double peso;
        private Double temperatura;
        private String veterinarioId;
        private String nombreVeterinario;
    }
}