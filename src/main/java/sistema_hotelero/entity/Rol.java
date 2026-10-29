
package sistema_hotelero.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    public Rol() {}

    public Long getIdRol() { return idRol; }
    public void setIdRol(Long id) { this.idRol = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
