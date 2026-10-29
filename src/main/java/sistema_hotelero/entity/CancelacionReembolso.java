
package sistema_hotelero.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cancelaciones_reembolsos")
public class CancelacionReembolso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cancelacion")
    private Long idCancelacion;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reserva", nullable = false, unique = true)
    private Reserva reserva;

    @Column(name = "fecha_cancelacion", insertable = false, updatable = false)
    private LocalDateTime fechaCancelacion;

    @Column(name = "motivo_cancelacion", columnDefinition = "TEXT")
    private String motivoCancelacion;

    @Column(name = "porcentaje_cargo", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentajeCargo = BigDecimal.ZERO;

    @Column(name = "monto_penalidad", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoPenalidad = BigDecimal.ZERO;

    @Column(name = "monto_reembolso", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoReembolso = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reembolso", nullable = false, length = 20)
    private EstadoReembolso estadoReembolso = EstadoReembolso.PROCESADO;

    public CancelacionReembolso() {}

    public Long getIdCancelacion() { return idCancelacion; }
    public void setIdCancelacion(Long id) { this.idCancelacion = id; }

    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }

    public LocalDateTime getFechaCancelacion() { return fechaCancelacion; }

    public String getMotivoCancelacion() { return motivoCancelacion; }
    public void setMotivoCancelacion(String motivo) { this.motivoCancelacion = motivo; }

    public BigDecimal getPorcentajeCargo() { return porcentajeCargo; }
    public void setPorcentajeCargo(BigDecimal porcentaje) { this.porcentajeCargo = porcentaje; }

    public BigDecimal getMontoPenalidad() { return montoPenalidad; }
    public void setMontoPenalidad(BigDecimal monto) { this.montoPenalidad = monto; }

    public BigDecimal getMontoReembolso() { return montoReembolso; }
    public void setMontoReembolso(BigDecimal monto) { this.montoReembolso = monto; }

    public EstadoReembolso getEstadoReembolso() { return estadoReembolso; }
    public void setEstadoReembolso(EstadoReembolso estado) { this.estadoReembolso = estado; }

    public enum EstadoReembolso {
        PROCESADO,
        PENDIENTE
    }
}
