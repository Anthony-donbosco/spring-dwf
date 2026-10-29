
package sistema_hotelero.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sistema_hotelero.entity.Pago;
import sistema_hotelero.entity.Reserva;
import sistema_hotelero.repository.PagoRepository;
import sistema_hotelero.repository.ReservaRepository;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final ReservaRepository reservaRepository;

    public PagoService(
            PagoRepository pagoRepository,
            ReservaRepository reservaRepository) {
        this.pagoRepository = pagoRepository;
        this.reservaRepository = reservaRepository;
    }

    @Transactional
    public Pago registrar(Pago pago) {
        if (pago.getReserva() == null ||
                pago.getReserva().getIdReserva() == null) {
            throw new IllegalArgumentException(
                    "Debes indicar la reserva del pago.");
        }

        Long idReserva = pago.getReserva().getIdReserva();

        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new IllegalArgumentException(
                        "La reserva indicada no existe."));

        if (pago.getMontoPagado() == null ||
                pago.getMontoPagado().signum() <= 0) {
            throw new IllegalArgumentException(
                    "El monto del pago debe ser mayor que cero.");
        }

        pago.setReserva(reserva);

        if (pago.getEstadoPago() == null) {
            pago.setEstadoPago(Pago.EstadoPago.PENDIENTE);
        }

        return pagoRepository.save(pago);
    }

    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }
}
