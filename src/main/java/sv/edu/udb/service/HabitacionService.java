package sv.edu.udb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.entity.Habitacion;
import sv.edu.udb.repository.HabitacionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;

    @Transactional(readOnly = true)
    public List<Habitacion> obtenerDisponibles(Long sucursalId, LocalDate checkIn, LocalDate checkOut) {
        if (sucursalId != null) {
            return habitacionRepository.findDisponibles(sucursalId);
        }
        return habitacionRepository.findByActivoTrueAndEstado("DISPONIBLE");
    }

    @Transactional(readOnly = true)
    public Habitacion obtenerPorId(Long id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + id));
    }
}
