package com.veterinaria.usuarios.controller;

import com.veterinaria.usuarios.model.Propietario;
import com.veterinaria.usuarios.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class PropietarioController {

    private final PropietarioService propietarioService;

    @Autowired
    public PropietarioController(PropietarioService propietarioService) {
        this.propietarioService = propietarioService;
    }

    @PostMapping
    public ResponseEntity<?> crearPropietario(@Valid @RequestBody Propietario propietario) {
        try {
            Propietario nuevoPropietario = propietarioService.crearPropietario(propietario);
            return new ResponseEntity<>(nuevoPropietario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    // READ - Obtener todos los propietarios
    @GetMapping
    public ResponseEntity<List<Propietario>> obtenerTodosLosPropietarios() {
        try {
            List<Propietario> propietarios = propietarioService.obtenerTodosLosPropietarios();
            return ResponseEntity.ok(propietarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // READ - Obtener propietario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPropietarioPorId(@PathVariable String id) {
        try {
            Optional<Propietario> propietario = propietarioService.obtenerPropietarioPorId(id);
            if (propietario.isPresent()) {
                return ResponseEntity.ok(propietario.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar propietario: " + e.getMessage());
        }
    }

    // READ - Buscar propietarios por nombre
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<List<Propietario>> buscarPorNombre(@PathVariable String nombre) {
        try {
            List<Propietario> propietarios = propietarioService.buscarPorNombre(nombre);
            return ResponseEntity.ok(propietarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // READ - Buscar propietario por email
    @GetMapping("/buscar/email/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        try {
            Optional<Propietario> propietario = propietarioService.buscarPorEmail(email);
            if (propietario.isPresent()) {
                return ResponseEntity.ok(propietario.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por email: " + e.getMessage());
        }
    }

    // READ - Buscar propietarios por teléfono
    @GetMapping("/buscar/telefono/{telefono}")
    public ResponseEntity<?> buscarPorTelefono(@PathVariable String telefono) {
        try {
            Optional<Propietario> propietario = propietarioService.buscarPorTelefono(telefono);
            if (propietario.isPresent()) {
                return ResponseEntity.ok(propietario.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por teléfono: " + e.getMessage());
        }
    }

    // UPDATE - Actualizar propietario completo
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPropietario(@PathVariable String id,
                                                   @Valid @RequestBody Propietario propietario) {
        try {
            Propietario propietarioActualizado = propietarioService.actualizarPropietario(id, propietario);
            if (propietarioActualizado != null) {
                return ResponseEntity.ok(propietarioActualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar: " + e.getMessage());
        }
    }

    // UPDATE - Actualización parcial con PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarPropietarioParcial(@PathVariable String id,
                                                          @RequestBody Propietario propietarioParcial) {
        try {
            Propietario propietarioActualizado = propietarioService.actualizarPropietarioParcial(id, propietarioParcial);
            if (propietarioActualizado != null) {
                return ResponseEntity.ok(propietarioActualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar parcialmente: " + e.getMessage());
        }
    }

    // DELETE - Eliminar propietario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPropietario(@PathVariable String id) {
        try {
            boolean eliminado = propietarioService.eliminarPropietario(id);
            if (eliminado) {
                return ResponseEntity.ok().body("Propietario eliminado exitosamente");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar: " + e.getMessage());
        }
    }

    // Método adicional - Contar total de propietarios
    @GetMapping("/count")
    public ResponseEntity<Long> contarPropietarios() {
        try {
            long count = propietarioService.contarPropietarios();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método adicional - Verificar si existe un propietario por email
    @GetMapping("/existe/email/{email}")
    public ResponseEntity<Boolean> existePorEmail(@PathVariable String email) {
        try {
            boolean existe = propietarioService.existePorEmail(email);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método adicional - Verificar si existe un propietario por teléfono
    @GetMapping("/existe/telefono/{telefono}")
    public ResponseEntity<Boolean> existePorTelefono(@PathVariable String telefono) {
        try {
            boolean existe = propietarioService.existePorTelefono(telefono);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}