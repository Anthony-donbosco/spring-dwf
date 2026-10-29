
package sistema_hotelero.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_hotelero.entity.Huesped;
import sistema_hotelero.service.HuespedService;

@RestController
@RequestMapping("/api/huespedes")
public class HuespedController {

    private final HuespedService service;

    public HuespedController(HuespedService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Huesped> registrar(@RequestBody Huesped huesped) {
        Huesped guardado = service.registrar(huesped);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }
}
