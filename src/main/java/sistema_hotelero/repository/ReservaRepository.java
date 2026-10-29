package sistema_hotelero.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sistema_hotelero.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Optional<Reserva> findByCodigoReserva(String codigoReserva);
}