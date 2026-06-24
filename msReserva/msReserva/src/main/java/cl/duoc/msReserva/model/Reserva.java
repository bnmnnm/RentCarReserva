package cl.duoc.msReserva.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
@Schema(description = "Entidad que representa una reserva en el sistema")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificado unico de la reserva", example = "1")
    private Integer id;

    @Column(name = "cliente_id", nullable = false)
    @Schema(description = "Identificador del cliente que realiza la reserva", example = "1")
    private Integer clienteId;

    @Column(name = "vehiculo_id", nullable = false)
    @Schema(description = "Identificador del vehículo a reservar", example = "1")
    private Integer vehiculoId;

    @Column(nullable = false)
    @Schema(description = "Fecha de inicio de la reserva", example = "2023-01-01")
    private Date fechaInicioReserva;

    @Column(nullable = false)
    @Schema(description = "Fecha de fin de la reserva", example = "2023-01-01")
    private Date fechaFinReserva;

    @ManyToOne
    @JoinColumn(name = "id_tipoReserva")
    @JsonBackReference
    @Schema(description = "Tipo de reserva asociado a la reserva")
    private TipoReserva tipoReserva;

}
