
package sistema_hotelero.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sistema_hotelero.entity.ReclamoSolicitud;
import sistema_hotelero.repository.ReclamoSolicitudRepository;
import sistema_hotelero.repository.ReservaRepository;
import sistema_hotelero.repository.HuespedRepository;

@Service
public class ReclamoSolicitudService {

    private final ReclamoSolicitudRepository reclamoRepository;
    private final ReservaRepository reservaRepository;
    private final HuespedRepository huespedRepository;

    public ReclamoSolicitudService(
            ReclamoSolicitudRepository reclamoRepository,
            ReservaRepository reservaRepository,
            HuespedRepository huespedRepository) {
        this.reclamoRepository = reclamoRepository;
        this.reservaRepository = reservaRepository;
        this.huespedRepository = huespedRepository;
    }

    @Transactional
    public ReclamoSolicitud registrar(ReclamoSolicitud reclamo) {
        if (reclamo.getReserva() == null
                || reclamo.getReserva().getIdReserva() == null) {
            throw new IllegalArgumentException(
                    "Debes indicar la reserva del reclamo.");
        }

        if (reclamo.getHuesped() == null
                || reclamo.getHuesped().getIdHuesped() == null) {
            throw new IllegalArgumentException(
                    "Debes indicar el huésped del reclamo.");
        }

        Long idReserva = reclamo.getReserva().getIdReserva();
        Long idHuesped = reclamo.getHuesped().getIdHuesped();

        var reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new IllegalArgumentException(
                        "La reserva indicada no existe."));

        var huesped = huespedRepository.findById(idHuesped)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El huésped indicado no existe."));

        if (!reserva.getHuesped().getIdHuesped().equals(idHuesped)) {
            throw new IllegalArgumentException(
                    "El huésped no corresponde a esa reserva.");
        }

        reclamo.setReserva(reserva);
        reclamo.setHuesped(huesped);

        return reclamoRepository.save(reclamo);
    }

    public List<ReclamoSolicitud> listarPorReserva(Long idReserva) {
        return reclamoRepository.findByReserva_IdReserva(idReserva);
    }
}
