package com.veterinaria.usuarios.dto;

import lombok.Data;
import java.time.LocalTime;
import java.time.DayOfWeek;

@Data
public class DisponibilidadDTO {
    private Long id;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long veterinarioId;
    private boolean activo;
}