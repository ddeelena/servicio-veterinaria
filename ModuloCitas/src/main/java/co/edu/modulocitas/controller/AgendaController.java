package co.edu.modulocitas.controller;

import co.edu.modulocitas.enums.Estado;
import co.edu.modulocitas.model.Cita;
import co.edu.modulocitas.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @GetMapping("/cita/{idCita}")
    public Optional<Cita> consultarCita(@PathVariable Integer idCita) {
        return agendaService.consultarCitaPorId(idCita);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Cita crearCita(@RequestBody Cita cita) {
        return agendaService.crearCita(cita);
    }

    @PutMapping("/actualizar/{idCita}")
    public Optional<Cita> actualizarCita(@PathVariable int idCita, @RequestBody Cita cita) {
        return agendaService.actualizarCita(idCita, cita);
    }

    @PutMapping("/estado/cita/{idCita}")
    public Optional<Cita> cambiarEstado(@PathVariable int idCita, @RequestBody Estado estado) {
        return agendaService.cambiarEstado(idCita, estado);
    }

    @GetMapping("/cita/allCitas")
    public List<Cita> consultarTodasLasCitas() {
        return agendaService.consultarCitas();
    }

    @GetMapping("/cita/estado/{estado}")
    public List<Cita> consultarCitaPorEstado(@PathVariable Estado estado) {
        return agendaService.consultarCitaPorEstado(estado);
    }

    @GetMapping("/cita/fecha/{fecha}")
    public List<Cita> consultarCitaPorFecha(@PathVariable LocalDate fecha) {
        return agendaService.consultarCitaPorFecha(fecha);
    }

    @GetMapping("/cita/hora/{hora}")
    public List<Cita> consultarCitaPorHora(@PathVariable LocalTime Hora) {
        return agendaService.consultarCitaPorHora(Hora);
    }

    @GetMapping("/cita/fechaYhora/{fecha}/{hora}")
    public List<Cita> consultarCitaPorFechaYHora(@PathVariable LocalDate fecha, @PathVariable LocalTime hora) {
        return agendaService.consultarCitaPorFechaYHora(fecha, hora);
    }

    @GetMapping("/cita/veterinario/{idVeterinario}")
    public List<Cita> consultarCitaPorVeterinario(@PathVariable String idVeterinario) {
        return agendaService.consultarCitaPorVeterinario(idVeterinario);
    }

    @GetMapping("/cita/paciente/{idPaciente}")
    public List<Cita> consultarCitaPorPaciente(@PathVariable String idPaciente) {
        return agendaService.consultarCitaPorPaciente(idPaciente);
    }

}
