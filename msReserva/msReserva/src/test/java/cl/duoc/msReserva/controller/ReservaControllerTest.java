package cl.duoc.msReserva.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.duoc.msReserva.dto.ClienteDTO;
import cl.duoc.msReserva.dto.ReservaDTO;
import cl.duoc.msReserva.dto.VehiculoDTO;
import cl.duoc.msReserva.model.Reserva;
import cl.duoc.msReserva.model.TipoReserva;
import cl.duoc.msReserva.service.ReservaService;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mock;

    @MockitoBean
    private ReservaService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Reserva ejemReserva;
    private ReservaDTO dtoEjemplo;

    @BeforeEach
    void setUp() {
        TipoReserva tipo = new TipoReserva();
        tipo.setId(1);
        tipo.setNombre("Por Semana");

        ejemReserva = new Reserva();
        ejemReserva.setId(1);
        ejemReserva.setClienteId(10);
        ejemReserva.setVehiculoId(20);
        ejemReserva.setFechaInicioReserva(new Date());
        ejemReserva.setFechaFinReserva(new Date());
        ejemReserva.setTipoReserva(tipo);

        dtoEjemplo = new ReservaDTO();
        dtoEjemplo.setId(1);
        dtoEjemplo.setClienteDTO(new ClienteDTO());
        dtoEjemplo.setVehiculoDTO(new VehiculoDTO());
        dtoEjemplo.setFechaInicioReserva(new Date());
        dtoEjemplo.setFechaFinReserva(new Date());
    }

    // ---------- listar ----------

    @Test
    public void listar_retorna200conLista() throws Exception {
        when(service.listar()).thenReturn(List.of(ejemReserva));

        mock.perform(get("/api/v1/reservas"))
            .andExpect(status().isOk());
    }

    @Test
    public void listar_retornaNoContentSiHayError() throws Exception {
        when(service.listar()).thenThrow(new RuntimeException());

        mock.perform(get("/api/v1/reservas"))
            .andExpect(status().isNoContent());
    }

    // ---------- guardar ----------

    @Test
    public void guardar_retorna200() throws Exception {
        when(service.guardarReserva(any(Reserva.class))).thenReturn(ejemReserva);

        mock.perform(post("/api/v1/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ejemReserva)))
            .andExpect(status().isOk());
    }

    @Test
    public void guardar_retornaNoContentSiHayError() throws Exception {
        when(service.guardarReserva(any(Reserva.class))).thenThrow(new RuntimeException());

        mock.perform(post("/api/v1/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ejemReserva)))
            .andExpect(status().isNoContent());
    }

    // ---------- buscarPorID ----------

    @Test
    public void buscarPorID_retorna200() throws Exception {
        when(service.buscarReserva(1)).thenReturn(ejemReserva);

        mock.perform(get("/api/v1/reservas/1"))
            .andExpect(status().isOk());
    }

    @Test
    public void buscarPorID_retorna404() throws Exception {
        when(service.buscarReserva(99)).thenThrow(new RuntimeException());

        mock.perform(get("/api/v1/reservas/99"))
            .andExpect(status().isNotFound());
    }

    // ---------- eliminarPorId ----------

    @Test
    public void eliminarPorId_retornaNoContent() throws Exception {
        doNothing().when(service).eliminarReserva(1);

        mock.perform(delete("/api/v1/reservas/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void eliminarPorId_retorna404() throws Exception {
        doThrow(new RuntimeException()).when(service).eliminarReserva(99);

        mock.perform(delete("/api/v1/reservas/99"))
            .andExpect(status().isNotFound());
    }

    // ---------- actualizar ----------

    @Test
    public void actualizar_retorna200() throws Exception {
        when(service.actualizarReserva(eq(1), any(Reserva.class))).thenReturn(ejemReserva);

        mock.perform(put("/api/v1/reservas/id/1")
                .param("clienteId", "10")
                .param("vehiculoId", "20"))
            .andExpect(status().isOk());
    }

    @Test
    public void actualizar_retorna404() throws Exception {
        when(service.actualizarReserva(eq(99), any(Reserva.class))).thenThrow(new RuntimeException());

        mock.perform(put("/api/v1/reservas/id/99")
                .param("clienteId", "10"))
            .andExpect(status().isNotFound());
    }

    // ---------- reservaCompleta (DTO) ----------

    @Test
    public void reservaCompleta_retorna200() throws Exception {
        when(service.obtenerDetalleReserva(1)).thenReturn(dtoEjemplo);

        mock.perform(get("/api/v1/reservas/dto/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void reservaCompleta_retornaNoContentSiHayError() throws Exception {
        when(service.obtenerDetalleReserva(99)).thenThrow(new RuntimeException());

        mock.perform(get("/api/v1/reservas/dto/99"))
            .andExpect(status().isNoContent());
    }
}