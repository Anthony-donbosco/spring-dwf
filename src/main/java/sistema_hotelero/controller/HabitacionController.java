
package sistema_hotelero.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_hotelero.entity.Habitacion;
import sistema_hotelero.service.HabitacionService;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    private final HabitacionService service;

    public HabitacionController(HabitacionService service) {
        this.service = service;
    }

    @GetMapping("/disponibles")
    public List<Habitacion> listarDisponibles() {
        return service.listarDisponibles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
