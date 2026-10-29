
package sistema_hotelero.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_hotelero.entity.Reserva;
import sistema_hotelero.service.ReservaService;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@RequestBody Reserva reserva) {
        Reserva guardada = service.crear(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    @GetMapping("/{codigoReserva}")
    public ResponseEntity<Reserva> buscarPorCodigo(
            @PathVariable String codigoReserva) {
        return service.buscarPorCodigo(codigoReserva)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
