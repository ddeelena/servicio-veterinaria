package com.veterinaria.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MascotaDTO {
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

    @NotNull(message = "El ID del propietario es obligatorio")
    private String propietarioId;
}