package sistema_hotelero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_hotelero.entity.ServicioAdicional;

public interface ServicioAdicionalRepository extends JpaRepository<ServicioAdicional, Long> {
}