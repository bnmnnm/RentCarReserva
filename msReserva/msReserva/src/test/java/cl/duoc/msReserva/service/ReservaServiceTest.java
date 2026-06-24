package cl.duoc.msReserva.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.msReserva.client.ClienteClient;
import cl.duoc.msReserva.client.VehiculoClient;
import cl.duoc.msReserva.dto.ClienteDTO;
import cl.duoc.msReserva.dto.ReservaDTO;
import cl.duoc.msReserva.dto.VehiculoDTO;
import cl.duoc.msReserva.model.Reserva;
import cl.duoc.msReserva.model.TipoReserva;
import cl.duoc.msReserva.repository.ReservaRepository;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    @Mock
    private ReservaRepository repo;

    @Mock
    private ClienteClient clienteCliente;

    @Mock
    private VehiculoClient clienteVehiculo;

    @InjectMocks
    private ReservaService service;

    private Reserva ejemReserva;
    private ClienteDTO ejemCliente;
    private VehiculoDTO ejemVehiculo;

    @BeforeEach
    void setUp() {
        TipoReserva tipo = new TipoReserva();
        tipo.setId(1);
        tipo.setNombre("Por Día");

        ejemReserva = new Reserva();
        ejemReserva.setId(1);
        ejemReserva.setClienteId(10);
        ejemReserva.setVehiculoId(20);
        ejemReserva.setFechaInicioReserva(new Date());
        ejemReserva.setFechaFinReserva(new Date());
        ejemReserva.setTipoReserva(tipo);

        ejemCliente = new ClienteDTO();
        ejemVehiculo = new VehiculoDTO();
    }

    @Test
    void listar_retornaLista() {
        when(repo.findAll()).thenReturn(List.of(ejemReserva));

        List<Reserva> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals(10, resultado.get(0).getClienteId());
    }

    @Test
    void buscarReserva_encontrado() {
        when(repo.findById(1)).thenReturn(Optional.of(ejemReserva));

        Reserva resultado = service.buscarReserva(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }

    @Test
    void buscarReserva_noEncontrado() {
        when(repo.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.buscarReserva(1);
        });

        assertEquals("no se encontro reserva", exception.getMessage());
    }

    @Test
    void guardarReserva_exitoso() {
        when(repo.save(ejemReserva)).thenReturn(ejemReserva);

        Reserva resultado = service.guardarReserva(ejemReserva);

        assertNotNull(resultado);
        verify(repo, times(1)).save(ejemReserva);
    }

    @Test
    void eliminarReserva_exitoso() {
        assertDoesNotThrow(() -> service.eliminarReserva(1));
        verify(repo, times(1)).deleteById(1);
    }

    @Test
    void actualizarReserva_exitoso() {
        TipoReserva tipoNuevo = new TipoReserva();
        tipoNuevo.setId(2);
        tipoNuevo.setNombre("Mensual");

        Reserva datosNuevos = new Reserva();
        datosNuevos.setTipoReserva(tipoNuevo);

        when(repo.findById(1)).thenReturn(Optional.of(ejemReserva));
        when(repo.save(ejemReserva)).thenReturn(ejemReserva);

        Reserva resultado = service.actualizarReserva(1, datosNuevos);

        assertNotNull(resultado);
        assertEquals("Mensual", resultado.getTipoReserva().getNombre());
        verify(repo, times(1)).save(ejemReserva);
    }

    @Test
    void actualizarReserva_noEncontrado() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.actualizarReserva(99, ejemReserva);
        });

        assertEquals("reserva no encontrada", exception.getMessage());
        verify(repo, times(0)).save(any(Reserva.class));
    }

    @Test
    void obtenerDetalleReserva_exitoso() {
        when(repo.findById(1)).thenReturn(Optional.of(ejemReserva));
        when(clienteCliente.buscarDTO(10)).thenReturn(ejemCliente);
        when(clienteVehiculo.obtenerVehiculoDTO(20)).thenReturn(ejemVehiculo);

        ReservaDTO resultado = service.obtenerDetalleReserva(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals(ejemCliente, resultado.getClienteDTO());
        assertEquals(ejemVehiculo, resultado.getVehiculoDTO());
    }
}