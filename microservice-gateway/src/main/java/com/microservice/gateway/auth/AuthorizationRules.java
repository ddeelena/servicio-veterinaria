package com.microservice.gateway.auth;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthorizationRules {

    private final Map<String, List<String>> roleRules = new HashMap<>();

    public AuthorizationRules() {
        roleRules.put("/api/auth/profile/", List.of("USUARIO", "VETERINARIO", "ADMIN"));
        roleRules.put("/api/auth/vslidate/", List.of("USUARIO", "VETERINARIO", "ADMIN"));
        roleRules.put("/api/disponibilidades/{Id}/", List.of("USUARIO","ADMIN"));
        roleRules.put("/api/disponibilidades/veterinario/{veterinarioId}", List.of("USUARIO","ADMIN"));
        roleRules.put("/api/disponibilidades/veterinario/{veterinarioId}/activas", List.of("USUARIO","ADMIN"));
        roleRules.put("/api/disponibilidades/verificar/{veterinarioId}", List.of("USUARIO","ADMIN"));
        roleRules.put("/api/disponibilidades/veterinario/{veterinarioId}/dia", List.of("USUARIO","ADMIN"));
        roleRules.put("/api/disponibilidades/create", List.of("ADMIN", "VETERINARIO"));
        roleRules.put("/api/disponibilidades/update/{id}", List.of("ADMIN", "VETERINARIO"));
        roleRules.put("/api/disponibilidades/delete/{id}", List.of("ADMIN", "VETERINARIO"));
        roleRules.put("/api/mascotas/{id}", List.of("USUARIO", "VETERINARIO"));
        roleRules.put("/api/mascotas/propietario/{propietarioId}", List.of("USUARIO", "VETERINARIO", "ADMIN"));
        roleRules.put("/api/mascotas/update/{id}", List.of( "ADMIN"));
        roleRules.put("/api/mascotas/delete/{id}", List.of( "ADMIN"));
        roleRules.put("/api/mascotas/nombre/{id}", List.of( "VETERINARIO","ADMIN"));
        roleRules.put("/api/usuarios/all", List.of( "ADMIN"));

        roleRules.put("servicios/cita/{idCita}", List.of( "USUARIO", "ADMIN"));
        roleRules.put("/agenda/crear", List.of( "USUARIO", "VETERINARIO"));
        roleRules.put("agenda/actualizar/{idCita}", List.of( "USUARIO", "VETERINARIO"));
        roleRules.put("agenda/estado/cita/{idCita}", List.of( "USUARIO", "VETERINARIO"));
        roleRules.put("agenda/cita/allCitas", List.of("VETERINARIO"));
        roleRules.put("agenda/cita/fecha/{fecha}", List.of( "USUARIO", "VETERINARIO", "ADMIN"));
        roleRules.put("agenda/cita/hora/{hora}", List.of( "USUARIO", "VETERINARIO","ADMIN"));
        roleRules.put("agenda/cita/fechaYhora/{fecha}/{hora}", List.of( "USUARIO", "VETERINARIO","ADMIN"));
        roleRules.put("agenda/cita/veterinario/{idVeterinario}", List.of("VETERINARIO","ADMIN"));
        roleRules.put("agenda/cita/paciente/{idPaciente}", List.of("VETERINARIO","ADMIN"));
        roleRules.put("servicios/{idServicio}", List.of("USUARIO"));
        roleRules.put("servicios/actualizar/{idServicio}", List.of("ADMIN"));
        roleRules.put("servicios/crear", List.of("ADMIN"));
        roleRules.put("historiaClinica/historia/{idHistoria}", List.of("VETERINARIO"));
        roleRules.put("historiaClinica/paciente/{idPaciente}", List.of("VETERINARIO"));
        roleRules.put("historiaClinica/cita/{idCita}", List.of("VETERINARIO"));
        roleRules.put("historiaClinica/veterinario/{idVeterinario}", List.of("VETERINARIO"));
        roleRules.put("historiaClinica/crear", List.of("VETERINARIO"));
        roleRules.put("historiaClinica/editar/{idHistoria}", List.of("VETERINARIO"));
        roleRules.put("historiaClinica/eliminar/{idHistoria}", List.of("VETERINARIO"));

        roleRules.put("/api/tienda/productos", List.of("admin")); // Admin y veterinario


        roleRules.put("/api/pagos", List.of("admin")); // Solo admin
        roleRules.put("/api/citas", List.of("paciente", "veterinario", "admin"));// Todos

        // Agrega más rutas según necesites
    }

    public boolean isAccessAllowed(String path, String role) {
        return roleRules.entrySet().stream()
                .filter(entry -> path.startsWith(entry.getKey()))
                .anyMatch(entry -> entry.getValue().contains(role));
    }
}
