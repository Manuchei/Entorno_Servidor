package cajeroweb.modelo.dao;

import cajeroweb.modelo.entidades.Prestamo;
import cajeroweb.modelo.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrestamoDaoImplJpaMy8 implements PrestamoDao {

    @Autowired
    private PrestamoRepository prestamoRepo;

    @Override
    public List<Prestamo> buscarPorCuenta(int idCuenta) {
        return prestamoRepo.findByCuentaIdCuenta(idCuenta);
    }

    @Override
    public int altaPrestamo(Prestamo prestamo) {
        try {
            prestamoRepo.save(prestamo);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int eliminarPrestamo(int idPrestamo) {
        try {
            prestamoRepo.deleteById(idPrestamo);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
