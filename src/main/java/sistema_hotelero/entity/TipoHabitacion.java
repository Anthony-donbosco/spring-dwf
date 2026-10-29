
package sistema_hotelero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "tipos_habitacion")
public class TipoHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Long idTipo;

    @NotBlank(message = "El nombre del tipo es obligatorio")
    @Column(name = "nombre_tipo", nullable = false, unique = true, length = 100)
    private String nombreTipo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Min(value = 1, message = "Debe permitir al menos un adulto")
    @Max(value = 10, message = "No puede superar 10 adultos")
    @Column(name = "capacidad_adultos", nullable = false)
    private Integer capacidadAdultos;

    @Min(value = 0, message = "La capacidad de niños no puede ser negativa")
    @Max(value = 6, message = "No puede superar 6 niños")
    @Column(name = "capacidad_ninos", nullable = false)
    private Integer capacidadNinos = 0;

    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
    @Column(name = "precio_base_sugerido", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioBaseSugerido;

    public TipoHabitacion() {
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCapacidadAdultos() {
        return capacidadAdultos;
    }

    public void setCapacidadAdultos(Integer capacidadAdultos) {
        this.capacidadAdultos = capacidadAdultos;
    }

    public Integer getCapacidadNinos() {
        return capacidadNinos;
    }

    public void setCapacidadNinos(Integer capacidadNinos) {
        this.capacidadNinos = capacidadNinos;
    }

    public BigDecimal getPrecioBaseSugerido() {
        return precioBaseSugerido;
    }

    public void setPrecioBaseSugerido(BigDecimal precioBaseSugerido) {
        this.precioBaseSugerido = precioBaseSugerido;
    }
}
