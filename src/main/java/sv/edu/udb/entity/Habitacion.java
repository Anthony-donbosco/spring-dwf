package sv.edu.udb.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "habitaciones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Long idHabitacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sucursal", nullable = false)
    private SucursalHotel sucursal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoHabitacion tipoHabitacion;

    @Column(name = "numero_habitacion", nullable = false, length = 20)
    private String numeroHabitacion;

    @Column(nullable = false)
    private Integer piso;

    @Column(name = "precio_noche", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioNoche;

    @Column(nullable = false, length = 30)
    private String estado = "DISPONIBLE";

    @Column(name = "descripcion_vista", length = 150)
    private String descripcionVista;

    @Column(nullable = false)
    private Boolean activo = true;
}
