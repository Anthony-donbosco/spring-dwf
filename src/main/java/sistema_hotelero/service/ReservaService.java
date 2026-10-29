
package sistema_hotelero.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sistema_hotelero.entity.*;
import sistema_hotelero.repository.*;

@Service
public class ReservaService {

    private final ReservaRepository repository;
    private final CancelacionReembolsoRepository cancelacionRepository;
    private final HuespedRepository huespedRepository;
    private final HabitacionRepository habitacionRepository;
    private final PoliticaCancelacionRepository politicaRepository;

    public ReservaService(
            ReservaRepository repository,
            CancelacionReembolsoRepository cancelacionRepository,
            HuespedRepository huespedRepository,
            HabitacionRepository habitacionRepository,
            PoliticaCancelacionRepository politicaRepository) {
        this.repository = repository;
        this.cancelacionRepository = cancelacionRepository;
        this.huespedRepository = huespedRepository;
        this.habitacionRepository = habitacionRepository;
        this.politicaRepository = politicaRepository;
    }

    public Optional<Reserva> buscarPorCodigo(String codigo) {
        return repository.findByCodigoReserva(codigo);
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Reserva crear(Reserva reserva) {
        if (reserva.getHuesped() == null
                || reserva.getHuesped().getIdHuesped() == null) {
            throw new IllegalArgumentException("Debes indicar el huésped.");
        }

        if (reserva.getHabitacion() == null
                || reserva.getHabitacion().getIdHabitacion() == null) {
            throw new IllegalArgumentException("Debes indicar la habitación.");
        }

        if (reserva.getPoliticaCancelacion() == null
                || reserva.getPoliticaCancelacion().getIdPolitica() == null) {
            throw new IllegalArgumentException(
                    "Debes indicar la política de cancelación.");
        }

        LocalDate entrada = reserva.getFechaCheckin();
        LocalDate salida = reserva.getFechaCheckout();

        if (entrada == null || salida == null
                || !salida.isAfter(entrada)
                || entrada.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Las fechas de la reserva no son válidas.");
        }

        Huesped huesped = huespedRepository.findById(
                reserva.getHuesped().getIdHuesped())
                .orElseThrow(() -> new IllegalArgumentException(
                        "El huésped no existe."));

        Habitacion habitacion = habitacionRepository.findById(
                reserva.getHabitacion().getIdHabitacion())
                .orElseThrow(() -> new IllegalArgumentException(
                        "La habitación no existe."));

        PoliticaCancelacion politica = politicaRepository.findById(
                reserva.getPoliticaCancelacion().getIdPolitica())
                .orElseThrow(() -> new IllegalArgumentException(
                        "La política de cancelación no existe."));

        if (!Boolean.TRUE.equals(habitacion.getActivo())
                || habitacion.getEstado() != Habitacion.EstadoHabitacion.DISPONIBLE) {
            throw new IllegalArgumentException(
                    "La habitación no está disponible para reservar.");
        }

        boolean existeCruce = repository.findAll().stream()
                .filter(r -> r.getHabitacion().getIdHabitacion()
                        .equals(habitacion.getIdHabitacion()))
                .filter(r -> r.getEstadoReserva()
                        != Reserva.EstadoReserva.CANCELADA
                        && r.getEstadoReserva()
                        != Reserva.EstadoReserva.CHECKED_OUT
                        && r.getEstadoReserva()
                        != Reserva.EstadoReserva.NO_SHOW)
                .anyMatch(r -> entrada.isBefore(r.getFechaCheckout())
                        && salida.isAfter(r.getFechaCheckin()));

        if (existeCruce) {
            throw new IllegalArgumentException(
                    "La habitación ya tiene una reserva en esas fechas.");
        }

        if (reserva.getCantidadHuespedes() == null
                || reserva.getCantidadHuespedes() < 1
                || reserva.getCantidadHuespedes()
                        > habitacion.getTipoHabitacion().getCapacidadAdultos()
                        + habitacion.getTipoHabitacion().getCapacidadNinos()) {
            throw new IllegalArgumentException(
                    "La cantidad de huéspedes supera la capacidad permitida.");
        }

        BigDecimal precio = habitacion.getPrecioNoche();
        long noches = ChronoUnit.DAYS.between(entrada, salida);
        BigDecimal totalHospedaje = precio.multiply(
                BigDecimal.valueOf(noches));

        BigDecimal servicios = reserva.getMontoServicios() == null
                ? BigDecimal.ZERO : reserva.getMontoServicios();

        BigDecimal impuestos = totalHospedaje
                .add(servicios)
                .multiply(new BigDecimal("0.13"));

        reserva.setHuesped(huesped);
        reserva.setHabitacion(habitacion);
        reserva.setPoliticaCancelacion(politica);
        reserva.setCodigoReserva("RES-" +
                UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        reserva.setPrecioNochePactado(precio);
        reserva.setTotalHospedaje(totalHospedaje);
        reserva.setMontoServicios(servicios);
        reserva.setMontoImpuestos(impuestos);
        reserva.setMontoTotal(totalHospedaje.add(servicios).add(impuestos));
        reserva.setEstadoReserva(Reserva.EstadoReserva.PENDIENTE);

        return repository.save(reserva);
    }

    @Transactional
    public CancelacionReembolso cancelar(Long id, String motivo) {
        Reserva reserva = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "La reserva no existe."));

        if (reserva.getEstadoReserva() == Reserva.EstadoReserva.CANCELADA) {
            throw new IllegalArgumentException("La reserva ya está cancelada.");
        }

        if (reserva.getEstadoReserva() == Reserva.EstadoReserva.CHECKED_IN
                || reserva.getEstadoReserva() == Reserva.EstadoReserva.CHECKED_OUT) {
            throw new IllegalArgumentException(
                    "No se puede cancelar una reserva que ya inició o finalizó.");
        }

        boolean yaCancelada = cancelacionRepository.findAll().stream()
                .anyMatch(c -> c.getReserva().getIdReserva().equals(id));

        if (yaCancelada) {
            throw new IllegalArgumentException(
                    "Ya existe un registro de cancelación.");
        }

        PoliticaCancelacion politica = reserva.getPoliticaCancelacion();

        long horasRestantes = ChronoUnit.HOURS.between(
                LocalDateTime.now(),
                reserva.getFechaCheckin().atStartOfDay());

        BigDecimal porcentaje = horasRestantes
                >= politica.getHorasLimiteGratis()
                ? BigDecimal.ZERO : politica.getPorcentajePenalidad();

        BigDecimal total = reserva.getMontoTotal();
        BigDecimal penalidad = total.multiply(porcentaje)
                .divide(new BigDecimal("100"), 2,
                        java.math.RoundingMode.HALF_UP);

        BigDecimal reembolso = total.subtract(penalidad)
                .max(BigDecimal.ZERO);

        CancelacionReembolso cancelacion = new CancelacionReembolso();
        cancelacion.setReserva(reserva);
        cancelacion.setMotivoCancelacion(motivo);
        cancelacion.setPorcentajeCargo(porcentaje);
        cancelacion.setMontoPenalidad(penalidad);
        cancelacion.setMontoReembolso(reembolso);
        cancelacion.setEstadoReembolso(
                CancelacionReembolso.EstadoReembolso.PENDIENTE);

        reserva.setEstadoReserva(Reserva.EstadoReserva.CANCELADA);
        repository.save(reserva);

        return cancelacionRepository.save(cancelacion);
    }
}
