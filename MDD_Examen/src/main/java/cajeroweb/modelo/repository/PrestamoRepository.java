package cajeroweb.modelo.repository;

import cajeroweb.modelo.entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    List<Prestamo> findByCuentaIdCuenta(int idCuenta);
}
