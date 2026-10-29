package sistema_hotelero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_hotelero.entity.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}