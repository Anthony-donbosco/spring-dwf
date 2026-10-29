package sistema_hotelero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_hotelero.entity.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
}