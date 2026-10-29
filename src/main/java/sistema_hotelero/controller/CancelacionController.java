
package sistema_hotelero.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_hotelero.entity.CancelacionReembolso;
import sistema_hotelero.service.CancelacionService;

@RestController
@RequestMapping("/api/reservas")
public class CancelacionController {

    private final CancelacionService service;

    public CancelacionController(CancelacionService service) {
        this.service = service;
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<CancelacionReembolso> cancelar(
            @PathVariable Long id,
            @RequestBody(required = false) Map<String, String> datos) {

        String motivo = datos == null
                ? "Sin motivo especificado"
                : datos.getOrDefault("motivo", "Sin motivo especificado");

        return ResponseEntity.ok(service.cancelar(id, motivo));
    }
}
