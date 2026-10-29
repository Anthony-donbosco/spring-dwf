
package sistema_hotelero.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "politicas_cancelacion")
public class PoliticaCancelacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_politica")
    private Long idPolitica;

    @Column(name = "nombre_politica", nullable = false, length = 100)
    private String nombrePolitica;

    @Column(name = "horas_limite_gratis", nullable = false)
    private Integer horasLimiteGratis = 48;

    @Column(name = "porcentaje_penalidad", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentajePenalidad = BigDecimal.ZERO;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    public PoliticaCancelacion() {}

    public Long getIdPolitica() { return idPolitica; }
    public void setIdPolitica(Long id) { this.idPolitica = id; }

    public String getNombrePolitica() { return nombrePolitica; }
    public void setNombrePolitica(String nombre) { this.nombrePolitica = nombre; }

    public Integer getHorasLimiteGratis() { return horasLimiteGratis; }
    public void setHorasLimiteGratis(Integer horas) { this.horasLimiteGratis = horas; }

    public BigDecimal getPorcentajePenalidad() { return porcentajePenalidad; }
    public void setPorcentajePenalidad(BigDecimal porcentaje) { this.porcentajePenalidad = porcentaje; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
