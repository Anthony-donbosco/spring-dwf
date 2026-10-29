
package sistema_hotelero.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sistema_hotelero.entity.Huesped;
import sistema_hotelero.repository.HuespedRepository;

@Service
public class HuespedService {

    private final HuespedRepository repository;

    public HuespedService(HuespedRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Huesped registrar(Huesped huesped) {
        if (repository.existsByNumeroDocumento(huesped.getNumeroDocumento())) {
            throw new IllegalArgumentException(
                    "Ya existe un huésped con ese número de documento.");
        }

        if (repository.existsByCorreoElectronico(huesped.getCorreoElectronico())) {
            throw new IllegalArgumentException(
                    "Ya existe un huésped con ese correo electrónico.");
        }

        return repository.save(huesped);
    }
}
   