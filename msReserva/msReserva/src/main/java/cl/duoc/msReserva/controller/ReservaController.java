package cl.duoc.msReserva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.msReserva.dto.ReservaDTO;
import cl.duoc.msReserva.model.Reserva;
import cl.duoc.msReserva.service.ReservaService;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public ResponseEntity<List<Reserva>> listar(){

        try {
            List<Reserva> reserva = service.listar();
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
        
    }

    @PostMapping
    public ResponseEntity<Reserva> guardar(@RequestBody Reserva reserva){
        try {
            Reserva reservaNueva = service.guardarReserva(reserva);
            return ResponseEntity.ok(reservaNueva);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarPorID(@PathVariable Integer id){
        try {
            Reserva reserva = service.buscarReserva(id);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer id){
        try {
            service.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Reserva> actualizar(@PathVariable Integer id, Reserva reservaActualizado){
        try {
            Reserva reserva = service.actualizarReserva(id, reservaActualizado);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ReservaDTO> reservaCompleta(@PathVariable Integer id){
        try {
            ReservaDTO reserva = service.obtenerDetalleReserva(id);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

}
