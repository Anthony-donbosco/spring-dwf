
package sistema_hotelero.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reclamos_solicitudes")
public class ReclamoSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reclamo")
    private Long idReclamo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_huesped", nullable = false)
    private Huesped huesped;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_solicitud", nullable = false, length = 30)
    private TipoSolicitud tipoSolicitud;

    @Column(nullable = false, length = 150)
    private String asunto;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoReclamo estado = EstadoReclamo.ABIERTO;

    @Column(name = "respuesta_personal", columnDefinition = "TEXT")
    private String respuestaPersonal;

    public ReclamoSolicitud() {}

    public Long getIdReclamo() { return idReclamo; }
    public void setIdReclamo(Long id) { this.idReclamo = id; }

    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }

    public Huesped getHuesped() { return huesped; }
    public void setHuesped(Huesped huesped) { this.huesped = huesped; }

    public TipoSolicitud getTipoSolicitud() { return tipoSolicitud; }
    public void setTipoSolicitud(TipoSolicitud tipo) { this.tipoSolicitud = tipo; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }

    public EstadoReclamo getEstado() { return estado; }
    public void setEstado(EstadoReclamo estado) { this.estado = estado; }

    public String getRespuestaPersonal() { return respuestaPersonal; }
    public void setRespuestaPersonal(String respuesta) { this.respuestaPersonal = respuesta; }

    public enum TipoSolicitud {
        QUEJA,
        MANTENIMIENTO,
        FACTURACION,
        SUGERENCIA
    }

    public enum EstadoReclamo {
        ABIERTO,
        EN_PROCESO,
        RESUELTO,
        CERRADO
    }
}
