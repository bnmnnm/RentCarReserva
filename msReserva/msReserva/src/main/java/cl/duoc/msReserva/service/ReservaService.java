package cl.duoc.msReserva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.msReserva.client.ClienteClient;
import cl.duoc.msReserva.dto.ClienteDTO;
import cl.duoc.msReserva.dto.ReservaDTO;
import cl.duoc.msReserva.dto.TipoReservaDTO;
import cl.duoc.msReserva.model.Reserva;
import cl.duoc.msReserva.repository.ReservaRepository;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository repo;

    @Autowired
    private ClienteClient clienteCliente;

    public List<Reserva> listar(){
        return repo.findAll();
    }

    public Reserva buscarReserva(Integer id){
        return repo.findById(id)
        .orElseThrow(() -> new RuntimeException("no se encontro reserva"));
    }

    public Reserva guardarReserva(Reserva reserva){
        return repo.save(reserva);
        
    }

    public void eliminarReserva(Integer id){
        repo.deleteById(id);
    }

    public Reserva actualizarReserva(Integer id, Reserva reservaActualizar){
        Reserva reserva = repo.findById(id).orElseThrow(() -> new RuntimeException("reserva no encontrada"));
        
        if(reservaActualizar.getTipoReserva() != null){
            reserva.setTipoReserva(reservaActualizar.getTipoReserva());
            
        }
        return repo.save(reserva);
    }

    public ReservaDTO obtenerDetalleReserva(Integer id){
        Reserva reserva = repo.findById(id).orElseThrow(() -> new RuntimeException("no se encontro la reserva"));

        ClienteDTO cliente = clienteCliente.buscarDTO(id);

        TipoReservaDTO tipoReservaDTO = new TipoReservaDTO(reserva.getTipoReserva().getId(), reserva.getTipoReserva().getNombre());

        ReservaDTO reservaCompleta = new ReservaDTO();

        reservaCompleta.setId(reserva.getId());
        reservaCompleta.setClienteDTO(cliente);
        reservaCompleta.setTipoReservaDTO(tipoReservaDTO);


        return reservaCompleta;
        
    }



}
