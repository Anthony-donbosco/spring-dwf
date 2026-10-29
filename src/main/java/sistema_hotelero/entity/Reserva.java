
package sistema_hotelero.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @Column(name = "codigo_reserva", nullable = false, unique = true, length = 30)
    private String codigoReserva;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_huesped", nullable = false)
    private Huesped huesped;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_habitacion", nullable = false)
    private Habitacion habitacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_politica", nullable = false)
    private PoliticaCancelacion politicaCancelacion;

    @Column(name = "fecha_reserva", insertable = false, updatable = false)
    private LocalDateTime fechaReserva;

    @Column(name = "fecha_checkin", nullable = false)
    private LocalDate fechaCheckin;

    @Column(name = "fecha_checkout", nullable = false)
    private LocalDate fechaCheckout;

    @Column(name = "cantidad_huespedes", nullable = false)
    private Integer cantidadHuespedes;

    @Column(name = "precio_noche_pactado", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioNochePactado;

    @Column(name = "total_hospedaje", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalHospedaje = BigDecimal.ZERO;

    @Column(name = "monto_servicios", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoServicios = BigDecimal.ZERO;

    @Column(name = "monto_impuestos", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoImpuestos = BigDecimal.ZERO;

    @Column(name = "monto_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reserva", nullable = false, length = 20)
    private EstadoReserva estadoReserva = EstadoReserva.PENDIENTE;

    @Column(name = "peticiones_especiales", columnDefinition = "TEXT")
    private String peticionesEspeciales;

    public Reserva() {}

    public Long getIdReserva() { return idReserva; }
    public void setIdReserva(Long id) { this.idReserva = id; }

    public String getCodigoReserva() { return codigoReserva; }
    public void setCodigoReserva(String codigo) { this.codigoReserva = codigo; }

    public Huesped getHuesped() { return huesped; }
    public void setHuesped(Huesped huesped) { this.huesped = huesped; }

    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }

    public PoliticaCancelacion getPoliticaCancelacion() { return politicaCancelacion; }
    public void setPoliticaCancelacion(PoliticaCancelacion politica) { this.politicaCancelacion = politica; }

    public LocalDateTime getFechaReserva() { return fechaReserva; }

    public LocalDate getFechaCheckin() { return fechaCheckin; }
    public void setFechaCheckin(LocalDate fecha) { this.fechaCheckin = fecha; }

    public LocalDate getFechaCheckout() { return fechaCheckout; }
    public void setFechaCheckout(LocalDate fecha) { this.fechaCheckout = fecha; }

    public Integer getCantidadHuespedes() { return cantidadHuespedes; }
    public void setCantidadHuespedes(Integer cantidad) { this.cantidadHuespedes = cantidad; }

    public BigDecimal getPrecioNochePactado() { return precioNochePactado; }
    public void setPrecioNochePactado(BigDecimal precio) { this.precioNochePactado = precio; }

    public BigDecimal getTotalHospedaje() { return totalHospedaje; }
    public void setTotalHospedaje(BigDecimal total) { this.totalHospedaje = total; }

    public BigDecimal getMontoServicios() { return montoServicios; }
    public void setMontoServicios(BigDecimal monto) { this.montoServicios = monto; }

    public BigDecimal getMontoImpuestos() { return montoImpuestos; }
    public void setMontoImpuestos(BigDecimal monto) { this.montoImpuestos = monto; }

    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal monto) { this.montoTotal = monto; }

    public EstadoReserva getEstadoReserva() { return estadoReserva; }
    public void setEstadoReserva(EstadoReserva estado) { this.estadoReserva = estado; }

    public String getPeticionesEspeciales() { return peticionesEspeciales; }
    public void setPeticionesEspeciales(String peticiones) { this.peticionesEspeciales = peticiones; }

    public enum EstadoReserva {
        PENDIENTE,
        CONFIRMADA,
        CHECKED_IN,
        CHECKED_OUT,
        CANCELADA,
        NO_SHOW
    }
}
