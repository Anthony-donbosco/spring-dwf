package sistema_hotelero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_hotelero.entity.TipoHabitacion;

public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Long> {
}