package cl.duoc.msReserva.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;

    @Column(name = "vehiculo_id", nullable = false)
    private Integer vehiculoId;

    @Column(nullable = false)
    private Date fechaInicioReserva;

    @Column(nullable = false)
    private Date fechaFinReserva;

    @ManyToOne
    @JoinColumn(name = "id_tipoReserva")
    @JsonBackReference
    private TipoReserva tipoReserva;

}
