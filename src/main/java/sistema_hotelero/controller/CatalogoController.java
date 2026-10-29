
package sistema_hotelero.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import sistema_hotelero.entity.TipoHabitacion;
import sistema_hotelero.entity.ServicioAdicional;
import sistema_hotelero.service.CatalogoService;

@RestController
@RequestMapping("/api")
public class CatalogoController {

    private final CatalogoService service;

    public CatalogoController(CatalogoService service) {
        this.service = service;
    }

    @GetMapping("/tipos-habitacion")
    public List<TipoHabitacion> listarTipos() {
        return service.listarTiposHabitacion();
    }

    @GetMapping("/servicios-adicionales")
    public List<ServicioAdicional> listarServicios() {
        return service.listarServiciosActivos();
    }
}
