package co.edu.modulocitas.controller;


import co.edu.modulocitas.model.Servicio;
import co.edu.modulocitas.service.ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/servicios")
@RequiredArgsConstructor
public class ServicioController {

    private final ServicioService servicioService;

    @GetMapping
    public List<Servicio> consultarServicios() {
        return servicioService.consultarServicios();
    }

    @GetMapping("/{idServicio}")
    public Optional<Servicio> consultarServicios(@PathVariable Integer idServicio) {
        return servicioService.consultarServicioPorId(idServicio);
    }

    @PostMapping("/crear")
    public Servicio agregarServicio(@RequestBody Servicio servicio) {
        return servicioService.crearServicio(servicio);
    }

    @PutMapping("/actualizar/{idServicio}")
    public Optional<Servicio> actualizarServicio(@PathVariable Integer idServicio, @RequestBody Servicio servicio) {
        return servicioService.actualizarServicio(idServicio,servicio);
    }

}
