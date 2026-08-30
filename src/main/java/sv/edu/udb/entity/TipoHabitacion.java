package sv.edu.udb.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tipos_habitacion")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TipoHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Long idTipo;

    @Column(name = "nombre_tipo", nullable = false, unique = true, length = 100)
    private String nombreTipo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "capacidad_adultos", nullable = false)
    private Integer capacidadAdultos;

    @Column(name = "capacidad_ninos", nullable = false)
    private Integer capacidadNinos = 0;

    @Column(name = "precio_base_sugerido", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioBaseSugerido;
}
