
package sistema_hotelero.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_hotelero.entity.ReclamoSolicitud;
import sistema_hotelero.service.ReclamoSolicitudService;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamoSolicitudController {

    private final ReclamoSolicitudService service;

    public ReclamoSolicitudController(ReclamoSolicitudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReclamoSolicitud> registrar(
            @RequestBody ReclamoSolicitud reclamo) {
        ReclamoSolicitud guardado = service.registrar(reclamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping("/reserva/{idReserva}")
    public List<ReclamoSolicitud> listarPorReserva(
            @PathVariable Long idReserva) {
        return service.listarPorReserva(idReserva);
    }
}
