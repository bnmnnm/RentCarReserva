package cl.duoc.msReserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.msReserva.model.TipoReserva;

@Repository
public interface TipoReservaRepository extends JpaRepository<TipoReserva, Integer>{

}
