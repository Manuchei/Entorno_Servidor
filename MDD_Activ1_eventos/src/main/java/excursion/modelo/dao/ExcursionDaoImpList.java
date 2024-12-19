package excursion.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import excursion.modelo.javabean.Excursion;

@Repository
public class ExcursionDaoImpList implements ExcursionDao {

	List<Excursion> lista;

	public ExcursionDaoImpList() {
		lista = new ArrayList<>();
		cargarDatos();
	}

	private void cargarDatos() {
		lista.add(new Excursion(01, "Viaje a Madrid", "Vigo", "Madrid", new Date(), 30, "TERMINADA", "N", 30, 10, 40.00,
				"Imagen", new Date()));
		lista.add(new Excursion(02, "Viaje a Barcelona", "Vigo", "Barcelona", new Date(), 30, "CANCELADA", "S", 30, 10,
				40.00, "Imagen", new Date()));
		lista.add(new Excursion(01, "Viaje a Santander", "Vigo", "Santander", new Date(), 30, "CREADA", "N", 30, 10,
				40.00, "Imagen", new Date()));
		lista.add(new Excursion(01, "Viaje a Lugo", "Vigo", "Lugo", new Date(), 30, "CANCELADA", "N", 30, 10, 40.00,
				"Imagen", new Date()));

	}

	@Override
	public Excursion findbyId(long idExcursion) {
		for (Excursion ele : lista) {
			if (ele.getIdExcursion() == idExcursion)
				return ele;
		}
		return null;
	}

	@Override
	public int insertOne(Excursion excursion) {
		if (lista.contains(excursion))
			return 0;
		return lista.add(excursion) ? 1 : 0;
	}

	@Override
	public int updateOne(Excursion excursion) {
		int pos = lista.indexOf(excursion);
		if (pos == -1)
			return 0;
		return (lista.set(pos, excursion) != null) ? 1 : 0;
	}

	@Override
	public int deleteOne(Excursion excursion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOne(long idExcursion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Excursion> findAll() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public List<Excursion> findbyDescripcionContaining(String cadena) {
		// TODO Auto-generated method stub
		return null;
	}

}
