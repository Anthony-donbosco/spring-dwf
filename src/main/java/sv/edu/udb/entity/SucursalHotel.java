package sv.edu.udb.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sucursales_hotel")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SucursalHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Long idSucursal;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @Column(nullable = false, length = 100)
    private String pais;

    @Column(nullable = false, length = 25)
    private String telefono;

    @Column(name = "correo_contacto", nullable = false, length = 150)
    private String correoContacto;

    @Column(name = "categoria_estrellas", nullable = false)
    private Integer categoriaEstrellas;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
