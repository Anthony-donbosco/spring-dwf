
package sistema_hotelero.service;

import org.springframework.stereotype.Service;
import sistema_hotelero.entity.CancelacionReembolso;

@Service
public class CancelacionService {

    private final ReservaService reservaService;

    public CancelacionService(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    public CancelacionReembolso cancelar(Long idReserva, String motivo) {
        return reservaService.cancelar(idReserva, motivo);
    }
}
