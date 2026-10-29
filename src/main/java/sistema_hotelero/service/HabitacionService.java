
package sistema_hotelero.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import sistema_hotelero.entity.Habitacion;
import sistema_hotelero.entity.Habitacion.EstadoHabitacion;
import sistema_hotelero.repository.HabitacionRepository;

@Service
public class HabitacionService {

    private final HabitacionRepository repository;

    public HabitacionService(HabitacionRepository repository) {
        this.repository = repository;
    }

    public List<Habitacion> listarDisponibles() {
        return repository.findAll().stream()
                .filter(h -> Boolean.TRUE.equals(h.getActivo()))
                .filter(h -> h.getEstado() == EstadoHabitacion.DISPONIBLE)
                .toList();
    }

    public Optional<Habitacion> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
