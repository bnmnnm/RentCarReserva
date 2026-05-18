package cl.duoc.msReserva.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Integer id;

    private ClienteDTO clienteDTO;

    private VehiculoDTO vehiculoDTO;

    private Date fechaInicioReserva;

    private Date fechaFinReserva;
}
