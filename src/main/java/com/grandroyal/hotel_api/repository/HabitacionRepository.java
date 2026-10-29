package com.grandroyal.hotel_api.repository;

import com.grandroyal.hotel_api.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    List<Habitacion> findBySucursal_IdSucursalAndEstado(Long idSucursal, String estado);
}