
package sistema_hotelero.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    @Column(name = "monto_pagado", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoPagado;

    @Column(name = "fecha_pago", insertable = false, updatable = false)
    private LocalDateTime fechaPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false, length = 30)
    private MetodoPago metodoPago;

    @Column(name = "referencia_transaccion", length = 100)
    private String referenciaTransaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false, length = 20)
    private EstadoPago estadoPago = EstadoPago.APROBADO;

    public Pago() {}

    public Long getIdPago() { return idPago; }
    public void setIdPago(Long id) { this.idPago = id; }

    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }

    public BigDecimal getMontoPagado() { return montoPagado; }
    public void setMontoPagado(BigDecimal monto) { this.montoPagado = monto; }

    public LocalDateTime getFechaPago() { return fechaPago; }

    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodo) { this.metodoPago = metodo; }

    public String getReferenciaTransaccion() { return referenciaTransaccion; }
    public void setReferenciaTransaccion(String referencia) { this.referenciaTransaccion = referencia; }

    public EstadoPago getEstadoPago() { return estadoPago; }
    public void setEstadoPago(EstadoPago estado) { this.estadoPago = estado; }

    public enum MetodoPago {
        TARJETA_CREDITO,
        TARJETA_DEBITO,
        TRANSFERENCIA,
        EFECTIVO
    }

    public enum EstadoPago {
        APROBADO,
        PENDIENTE,
        RECHAZADO,
        REEMBOLSADO
    }
}
