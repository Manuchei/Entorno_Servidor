package cajeroweb.modelo.dao;

import cajeroweb.modelo.entidades.Prestamo;
import java.util.List;

public interface PrestamoDao {
    List<Prestamo> buscarPorCuenta(int idCuenta);
    int altaPrestamo(Prestamo prestamo);
    int eliminarPrestamo(int idPrestamo);
}
