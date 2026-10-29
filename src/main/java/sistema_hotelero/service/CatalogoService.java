
package sistema_hotelero.service;

import java.util.List;
import org.springframework.stereotype.Service;
import sistema_hotelero.entity.TipoHabitacion;
import sistema_hotelero.entity.ServicioAdicional;
import sistema_hotelero.repository.TipoHabitacionRepository;
import sistema_hotelero.repository.ServicioAdicionalRepository;

@Service
public class CatalogoService {

    private final TipoHabitacionRepository tipoRepository;
    private final ServicioAdicionalRepository servicioRepository;

    public CatalogoService(
            TipoHabitacionRepository tipoRepository,
            ServicioAdicionalRepository servicioRepository) {
        this.tipoRepository = tipoRepository;
        this.servicioRepository = servicioRepository;
    }

    public List<TipoHabitacion> listarTiposHabitacion() {
        return tipoRepository.findAll();
    }

    public List<ServicioAdicional> listarServiciosActivos() {
        return servicioRepository.findAll().stream()
                .filter(s -> Boolean.TRUE.equals(s.getActivo()))
                .toList();
    }
}
