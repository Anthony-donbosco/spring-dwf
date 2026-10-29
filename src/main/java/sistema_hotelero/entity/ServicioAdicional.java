
package sistema_hotelero.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "servicios_adicionales")
public class ServicioAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "nombre_servicio", nullable = false, length = 100)
    private String nombreServicio;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cobro", nullable = false, length = 20)
    private TipoCobro tipoCobro;

    @Column(nullable = false)
    private Boolean activo = true;

    public ServicioAdicional() {}

    public Long getIdServicio() { return idServicio; }
    public void setIdServicio(Long id) { this.idServicio = id; }

    public String getNombreServicio() { return nombreServicio; }
    public void setNombreServicio(String nombre) { this.nombreServicio = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public TipoCobro getTipoCobro() { return tipoCobro; }
    public void setTipoCobro(TipoCobro tipoCobro) { this.tipoCobro = tipoCobro; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public enum TipoCobro {
        POR_PERSONA,
        POR_NOCHE,
        PAGO_UNICO
    }
}
