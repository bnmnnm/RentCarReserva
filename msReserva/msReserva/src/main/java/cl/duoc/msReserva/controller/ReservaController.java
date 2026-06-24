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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/reservas")
@Tag(name = "Reserva", description = "Controlador de reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    @Operation(summary = "Listar reservas", 
               description = "Obtiene una lista de todas las reservas registradas en el sistema."
            )
    public ResponseEntity<List<Reserva>> listar(){

        try {
            List<Reserva> reserva = service.listar();
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
        
    }

    @PostMapping
    @Operation(summary = "Guardar reserva",
                description = "Permite guardar una nueva reserva en el sistema."

    )
    public ResponseEntity<Reserva> guardar(@RequestBody Reserva reserva){
        try {
            Reserva reservaNueva = service.guardarReserva(reserva);
            return ResponseEntity.ok(reservaNueva);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reserva por ID",
                description = "Permite buscar una reserva específica en el sistema utilizando su ID único."
    )
    public ResponseEntity<Reserva> buscarPorID(@PathVariable Integer id){
        try {
            Reserva reserva = service.buscarReserva(id);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reserva por ID",
            description = "Permite eliminar una reserva especifica del sistema utilizando su ID unico"

    )
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer id){
        try {
            service.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/id/{id}")
    @Operation(summary = "Actualizar reserva por ID",
                description = "Permite actualizar la información de una reserva específica en el sistema utilizando su ID único."
    )
    public ResponseEntity<Reserva> actualizar(@PathVariable Integer id, Reserva reservaActualizado){
        try {
            Reserva reserva = service.actualizarReserva(id, reservaActualizado);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dto/{id}")
    @Operation(summary = "Obtener reserva completa por ID",
        description = "Permite obtener la informacion completa de una reserva especifica en el sistema utilizando el ID"
    )
    public ResponseEntity<ReservaDTO> reservaCompleta(@PathVariable Integer id){
        try {
            ReservaDTO reserva = service.obtenerDetalleReserva(id);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

}
