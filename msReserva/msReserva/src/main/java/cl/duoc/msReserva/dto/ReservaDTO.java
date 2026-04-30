package cl.duoc.msReserva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Integer id;

    private ClienteDTO clienteDTO;

    private TipoReservaDTO tipoReservaDTO;
}
