package sv.edu.udb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sv.edu.udb.entity.Habitacion;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    
    List<Habitacion> findByActivoTrueAndEstado(String estado);

    @Query("SELECT h FROM Habitacion h WHERE h.sucursal.idSucursal = :sucursalId AND h.activo = true AND h.estado = 'DISPONIBLE'")
    List<Habitacion> findDisponibles(@Param("sucursalId") Long sucursalId);
}
