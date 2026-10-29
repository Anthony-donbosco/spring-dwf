package com.grandroyal.hotel_api.service;

import com.grandroyal.hotel_api.model.Habitacion;
import com.grandroyal.hotel_api.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    // Método para listar habitaciones disponibles por sucursal
    public List<Habitacion> obtenerHabitacionesDisponibles(Long idSucursal) {
        return habitacionRepository.findBySucursal_IdSucursalAndEstado(idSucursal, "DISPONIBLE");
    }

    // Método para obtener todas las habitaciones del sistema
    public List<Habitacion> obtenerTodas() {
        return habitacionRepository.findAll();
    }
}