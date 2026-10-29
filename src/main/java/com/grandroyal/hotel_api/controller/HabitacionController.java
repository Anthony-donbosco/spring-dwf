package com.grandroyal.hotel_api.controller;

import com.grandroyal.hotel_api.model.Habitacion;
import com.grandroyal.hotel_api.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@CrossOrigin(origins = "*") // Permite que el Frontend consuma la API sin problemas de CORS
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    // Endpoint para obtener el catálogo completo o filtrar por sucursal
    // Ejemplo: GET /api/habitaciones/disponibles?idSucursal=1
    @GetMapping("/disponibles")
    public ResponseEntity<List<Habitacion>> listarDisponibles(@RequestParam(required = false) Long idSucursal) {
        if (idSucursal != null) {
            List<Habitacion> habitaciones = habitacionService.obtenerHabitacionesDisponibles(idSucursal);
            return ResponseEntity.ok(habitaciones);
        } else {
            List<Habitacion> habitaciones = habitacionService.obtenerTodas();
            return ResponseEntity.ok(habitaciones);
        }
    }
}