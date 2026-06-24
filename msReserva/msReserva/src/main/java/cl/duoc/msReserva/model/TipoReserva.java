package cl.duoc.msReserva.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TipoReserva")
@Schema(description = "Entidad que representa un tipo de reserva en el sistema.")
public class TipoReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del tipo de reserva", example = "1")
    private Integer id;

    @Column(nullable = false)
    @Schema(description = "Nombre del tipo de reserva", example = "Reserva de vehículo")
    private String nombre;

}
