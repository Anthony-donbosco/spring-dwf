package sv.edu.udb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sv.edu.udb.entity.Habitacion;
import sv.edu.udb.entity.SucursalHotel;
import sv.edu.udb.repository.SucursalHotelRepository;
import sv.edu.udb.service.HabitacionService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebViewController {

    private final HabitacionService habitacionService;
    private final SucursalHotelRepository sucursalRepository;

    /**
     * PANTALLA 1: Página Principal y Motor de Búsqueda
     */
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        LocalDate hoy = LocalDate.now();
        LocalDate checkInDefecto = hoy.plusDays(5);
        LocalDate checkOutDefecto = checkInDefecto.plusDays(3);

        List<SucursalHotel> sucursales = sucursalRepository.findByActivoTrue();
        
        model.addAttribute("sucursales", sucursales);
        model.addAttribute("checkInDefault", checkInDefecto);
        model.addAttribute("checkOutDefault", checkOutDefecto);
        model.addAttribute("titulo", "Inicio y Búsqueda de Habitaciones - Grand Royal");
        
        return "home";
    }

    /**
     * PANTALLA 2: Catálogo de Habitaciones con Resultados
     */
    @GetMapping("/catalogo")
    public String catalogo(
            @RequestParam(required = false, defaultValue = "1") Long sucursalId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            @RequestParam(required = false, defaultValue = "2") Integer huespedes,
            Model model) {

        if (checkIn == null) checkIn = LocalDate.now().plusDays(5);
        if (checkOut == null) checkOut = checkIn.plusDays(3);

        long noches = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (noches <= 0) noches = 1;

        List<Habitacion> habitaciones = habitacionService.obtenerDisponibles(sucursalId, checkIn, checkOut);
        SucursalHotel sucursalSeleccionada = sucursalRepository.findById(sucursalId).orElse(null);

        model.addAttribute("habitaciones", habitaciones);
        model.addAttribute("sucursal", sucursalSeleccionada);
        model.addAttribute("sucursalId", sucursalId);
        model.addAttribute("checkIn", checkIn);
        model.addAttribute("checkOut", checkOut);
        model.addAttribute("huespedes", huespedes);
        model.addAttribute("totalNoches", noches);
        model.addAttribute("totalResultados", habitaciones.size());
        model.addAttribute("titulo", "Catálogo de Habitaciones Disponibles");

        return "catalogo";
    }
}
