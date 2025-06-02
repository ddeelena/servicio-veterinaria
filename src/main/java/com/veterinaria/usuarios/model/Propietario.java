package com.veterinaria.usuarios.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Propietario {

    @Id
    private String id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe tener 10 dígitos")
    private String telefono;

    private String direccion;

    @DBRef
    private List<Mascota> mascotas = new ArrayList<>();

    // Métodos helper
    public void addMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public void removeMascota(Mascota mascota) {
        mascotas.remove(mascota);
    }
}