package sv.edu.udb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.entity.SucursalHotel;
import java.util.List;

@Repository
public interface SucursalHotelRepository extends JpaRepository<SucursalHotel, Long> {
    List<SucursalHotel> findByActivoTrue();
}
