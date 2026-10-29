package sistema_hotelero.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sistema_hotelero.entity.ReclamoSolicitud;

public interface ReclamoSolicitudRepository extends JpaRepository<ReclamoSolicitud, Long> {
    List<ReclamoSolicitud> findByReserva_IdReserva(Long idReserva);
}