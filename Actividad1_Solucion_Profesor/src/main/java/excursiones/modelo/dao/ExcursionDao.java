package excursiones.modelo.dao;

import java.util.List;

import excursiones.modelo.javabeans.Excursion;

public interface ExcursionDao {
	
	Excursion findById(int idExcursion);
	List<Excursion> findAll();
	int insertOne(Excursion excursion);
	int updateOne(Excursion excursion);
//	List<Excursion> findByEstado(String estado);
	List<Excursion> findByActivos();
	List<Excursion> findByCancelados();
	List<Excursion> findByDestacados();
	List<Excursion> findByTerminados();
	List<Excursion> findByPrecioMayor(double precio);
	List<Excursion> findByPrecioMenor(double precio);
	List<Excursion> findByPrecioEntre(double precioDe, double precioHasta);
	List<Excursion> findByOrigen(String origen);
	List<Excursion> findByDestino(String destino);


}
