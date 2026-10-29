
package sistema_hotelero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(
    name = "habitaciones",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_habitacion_sucursal_numero",
            columnNames = {"id_sucursal", "numero_habitacion"}
        )
    }
)
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Long idHabitacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sucursal", nullable = false)
    private SucursalHotel sucursal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoHabitacion tipoHabitacion;

    @NotBlank(message = "El número de habitación es obligatorio")
    @Column(name = "numero_habitacion", nullable = false, length = 20)
    private String numeroHabitacion;

    @Min(1)
    @Max(50)
    @Column(nullable = false)
    private Integer piso;

    @DecimalMin(value = "0.01", message = "El precio por noche debe ser mayor que cero")
    @Column(name = "precio_noche", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioNoche;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoHabitacion estado = EstadoHabitacion.DISPONIBLE;

    @Column(name = "descripcion_vista", columnDefinition = "TEXT")
    private String descripcionVista;

    @Column(nullable = false)
    private Boolean activo = true;

    public Habitacion() {
    }

    public Long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public SucursalHotel getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalHotel sucursal) {
        this.sucursal = sucursal;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public BigDecimal getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(BigDecimal precioNoche) {
        this.precioNoche = precioNoche;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }

    public String getDescripcionVista() {
        return descripcionVista;
    }

    public void setDescripcionVista(String descripcionVista) {
        this.descripcionVista = descripcionVista;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public enum EstadoHabitacion {
        DISPONIBLE,
        OCUPADA,
        MANTENIMIENTO,
        LIMPIEZA
    }
}
