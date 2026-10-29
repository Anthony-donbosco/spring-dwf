package sistema_hotelero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_hotelero.entity.Huesped;

public interface HuespedRepository extends JpaRepository<Huesped, Long> {
    boolean existsByNumeroDocumento(String numeroDocumento);

boolean existsByCorreoElectronico(String correoElectronico);
}