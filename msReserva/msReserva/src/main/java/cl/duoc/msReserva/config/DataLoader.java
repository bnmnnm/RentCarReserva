package cl.duoc.msReserva.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.msReserva.model.Reserva;
import cl.duoc.msReserva.model.TipoReserva;
import cl.duoc.msReserva.repository.ReservaRepository;
import cl.duoc.msReserva.repository.TipoReservaRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(TipoReservaRepository tipoReservaRepo,
                                   ReservaRepository reservaRepo){
        return args -> {
            if(tipoReservaRepo.count()>0){
                System.out.println("Base de datos ya inicializada");
            }else{
                TipoReserva tipo1 = new TipoReserva(null, "Pendiente");
                TipoReserva tipo2 = new TipoReserva(null, "Completada");
                TipoReserva tipo3 = new TipoReserva(null, "Cancelada");

                tipoReservaRepo.save(tipo1);
                tipoReservaRepo.save(tipo2);
                tipoReservaRepo.save(tipo3);

                Reserva reserva1 = new Reserva(null, 1, 1, new java.util.Date(), new java.util.Date(), tipo1);
                Reserva reserva2 = new Reserva(null, 2, 2, new java.util.Date(), new java.util.Date(), tipo2);
                Reserva reserva3 = new Reserva(null, 3, 3, new java.util.Date(), new java.util.Date(), tipo3);

                reservaRepo.save(reserva1);
                reservaRepo.save(reserva2);
                reservaRepo.save(reserva3);

                System.out.println("Base de datos inicializada");
            }
        };
    }
}
