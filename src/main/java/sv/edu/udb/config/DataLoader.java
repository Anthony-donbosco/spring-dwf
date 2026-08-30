package sv.edu.udb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sv.edu.udb.entity.Habitacion;
import sv.edu.udb.entity.SucursalHotel;
import sv.edu.udb.entity.TipoHabitacion;
import sv.edu.udb.repository.HabitacionRepository;
import sv.edu.udb.repository.SucursalHotelRepository;
import sv.edu.udb.repository.TipoHabitacionRepository;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final SucursalHotelRepository sucursalRepository;
    private final TipoHabitacionRepository tipoRepository;
    private final HabitacionRepository habitacionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (sucursalRepository.count() == 0) {
            // 1. Sucursales Hoteleras
            SucursalHotel s1 = sucursalRepository.save(SucursalHotel.builder()
                    .nombre("Hotel Royal San Salvador")
                    .direccion("Boulevard Los Próceres #123, San Salvador")
                    .ciudad("San Salvador")
                    .pais("El Salvador")
                    .telefono("+503 2250-8000")
                    .correoContacto("reservas.sansalvador@grandroyal.com")
                    .categoriaEstrellas(5)
                    .activo(true)
                    .build());

            SucursalHotel s2 = sucursalRepository.save(SucursalHotel.builder()
                    .nombre("Resort Costa del Sol Paradise")
                    .direccion("Km 72 Boulevard Costa del Sol, La Paz")
                    .ciudad("Costa del Sol")
                    .pais("El Salvador")
                    .telefono("+503 2340-9000")
                    .correoContacto("reservas.costadelsol@grandroyal.com")
                    .categoriaEstrellas(4)
                    .activo(true)
                    .build());

            // 2. Tipos de Habitación
            TipoHabitacion t1 = tipoRepository.save(TipoHabitacion.builder()
                    .nombreTipo("Individual Estándar")
                    .descripcion("Habitación ejecutiva con cama individual, escritorio de trabajo y baño privado.")
                    .capacidadAdultos(1)
                    .capacidadNinos(0)
                    .precioBaseSugerido(new BigDecimal("45.00"))
                    .build());

            TipoHabitacion t2 = tipoRepository.save(TipoHabitacion.builder()
                    .nombreTipo("Doble Confort")
                    .descripcion("Habitación con 2 camas matrimoniales, ideal para parejas o familias pequeñas.")
                    .capacidadAdultos(2)
                    .capacidadNinos(2)
                    .precioBaseSugerido(new BigDecimal("75.00"))
                    .build());

            TipoHabitacion t3 = tipoRepository.save(TipoHabitacion.builder()
                    .nombreTipo("Suite Ejecutiva con Jacuzzi")
                    .descripcion("Suite de lujo con sala de estar independiente, balcón con vista y jacuzzi.")
                    .capacidadAdultos(2)
                    .capacidadNinos(1)
                    .precioBaseSugerido(new BigDecimal("130.00"))
                    .build());

            TipoHabitacion t4 = tipoRepository.save(TipoHabitacion.builder()
                    .nombreTipo("Master Family Suite")
                    .descripcion("Suite familiar de dos ambientes con vista panorámica y equipamiento completo.")
                    .capacidadAdultos(4)
                    .capacidadNinos(2)
                    .precioBaseSugerido(new BigDecimal("190.00"))
                    .build());

            // 3. Habitaciones
            habitacionRepository.save(Habitacion.builder()
                    .sucursal(s1)
                    .tipoHabitacion(t1)
                    .numeroHabitacion("101")
                    .piso(1)
                    .precioNoche(new BigDecimal("45.00"))
                    .estado("DISPONIBLE")
                    .descripcionVista("Vista a la piscina central")
                    .activo(true)
                    .build());

            habitacionRepository.save(Habitacion.builder()
                    .sucursal(s1)
                    .tipoHabitacion(t2)
                    .numeroHabitacion("201")
                    .piso(2)
                    .precioNoche(new BigDecimal("75.00"))
                    .estado("DISPONIBLE")
                    .descripcionVista("Vista panorámica a la ciudad")
                    .activo(true)
                    .build());

            habitacionRepository.save(Habitacion.builder()
                    .sucursal(s1)
                    .tipoHabitacion(t3)
                    .numeroHabitacion("301")
                    .piso(3)
                    .precioNoche(new BigDecimal("130.00"))
                    .estado("DISPONIBLE")
                    .descripcionVista("Vista al Volcán de San Salvador")
                    .activo(true)
                    .build());

            habitacionRepository.save(Habitacion.builder()
                    .sucursal(s1)
                    .tipoHabitacion(t4)
                    .numeroHabitacion("205B")
                    .piso(2)
                    .precioNoche(new BigDecimal("190.00"))
                    .estado("DISPONIBLE")
                    .descripcionVista("Vista al mar y jardines")
                    .activo(true)
                    .build());

            System.out.println(">>> Datos de Habitaciones y Hoteles cargados exitosamente.");
        }
    }
}
