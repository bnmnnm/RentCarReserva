package cl.duoc.msReserva.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.msReserva.dto.ClienteDTO;

@FeignClient(name = "msCliente")
public interface ClienteClient {

    @GetMapping("/api/v1/clientes/dto/{id}")
    ClienteDTO buscarDTO(@PathVariable Integer id);

}
