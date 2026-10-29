package sistema_hotelero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_hotelero.entity.PoliticaCancelacion;

public interface PoliticaCancelacionRepository extends JpaRepository<PoliticaCancelacion, Long> {
}