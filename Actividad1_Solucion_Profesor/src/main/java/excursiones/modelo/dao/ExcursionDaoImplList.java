package excursiones.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import excursiones.modelo.javabeans.Excursion;
import excursiones.utilidades.Util;
@Repository
public class ExcursionDaoImplList implements ExcursionDao{
	
	private List<Excursion> lista;
	
	public ExcursionDaoImplList() {
		lista = new ArrayList<>();
		cargarDatos();
	}
	
	private void cargarDatos() {
		lista.add(new Excursion(1, "En bus a Barbate", "Madrid", "Barbate", Util.unaFecha("2024-12-06"), 1, "CREADO", "S", 56, 15, 40, "default.png", new Date()));
		lista.add(new Excursion(2, "En bus a Soria", "Madrid", "Soria", Util.unaFecha("2024-12-06"), 1, "CREADO", "S", 56, 15, 140, "default.png", new Date()));
		lista.add(new Excursion(3, "En bus a Pamplona", "Madrid", "Pamplona", Util.unaFecha("2024-12-06"), 1, "CREADO", "S", 56, 15, 240, "default.png", new Date()));
		lista.add(new Excursion(4, "En bus a Barcelona", "Madrid", "Barcelona", Util.unaFecha("2024-12-06"), 1, "CREADO", "N", 56, 15, 340, "default.png", new Date()));
		lista.add(new Excursion(5, "En bus a Alicante", "Madrid", "Alicante", Util.unaFecha("2024-12-06"), 1, "CANCELADO", "S", 56, 15, 25, "default.png", new Date()));
		lista.add(new Excursion(6, "En bus a Zaragoza", "Soria", "Zaragoza", Util.unaFecha("2024-12-06"), 1, "CANCELADO", "S", 56, 15, 125, "default.png", new Date()));
		lista.add(new Excursion(7, "En tren a Burgos", "Soria", "Burgos", Util.unaFecha("2024-12-06"), 1, "TERMINADO", "S", 56, 15, 440, "default.png", new Date()));
		lista.add(new Excursion(8, "En tren a Palencia", "Soria", "Palencia", Util.unaFecha("2024-12-06"), 1, "TERMINADO", "S", 56, 15, 1440, "default.png", new Date()));
		lista.add(new Excursion(9, "En tren a Pamplona", "Soria", "Pamplona", Util.unaFecha("2024-12-06"), 1, "TERMINADO", "S", 56, 15, 340, "default.png", new Date()));
		
	
	
	}

	@Override
	public Excursion findById(int idExcursion) {
		for (Excursion ex: lista) {
			if (ex.getIdExcursion() == idExcursion)
				return ex;

		}
		return null;
	}

	@Override
	public List<Excursion> findAll() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public int insertOne(Excursion excursion) {
		if (lista.contains(excursion))
			return 0;
		lista.add(excursion);
		return 1;
	}

	@Override
	public int updateOne(Excursion excursion) {
		int pos = lista.indexOf(excursion);
		if (pos == -1)
			return 0;
		lista.set(pos, excursion);
		return 1;
	}

	@Override
	public List<Excursion> findByActivos() {
		List<Excursion> aux = new ArrayList<>();
		for (Excursion ex: lista) {
			if (ex.getEstado().equals("CREADO"))
				aux.add(ex);
		}
		return aux;
	}

	@Override
	public List<Excursion> findByCancelados() {
		List<Excursion> aux = new ArrayList<>();
		for (Excursion ex: lista) {
			if (ex.getEstado().equals("CANCELADO"))
				aux.add(ex);
		}
		return aux;
	}

	@Override
	public List<Excursion> findByDestacados() {
		List<Excursion> aux = new ArrayList<>();
		for (Excursion ex: lista) {
			if (ex.getDestacado().equals("S"))
				aux.add(ex);
		}
		return aux;
	}

	@Override
	public List<Excursion> findByTerminados() {
		List<Excursion> aux = new ArrayList<>();
		for (Excursion ex: lista) {
			if (ex.getEstado().equals("TERMINADO"))
				aux.add(ex);
		}
		return aux;
	}

	@Override
	public List<Excursion> findByPrecioMayor(double precio) {
		List<Excursion> aux = new ArrayList<>();
		for (Excursion ex: lista) {
			if (ex.getPrecioUnitario() > precio)
				aux.add(ex);
		}
		return aux;
	}

	@Override
	public List<Excursion> findByPrecioMenor(double precio) {
		List<Excursion> aux = new ArrayList<>();
		for (Excursion ex: lista) {
			if (ex.getPrecioUnitario() < precio)
				aux.add(ex);
		}
		return aux;
	}

	@Override
	public List<Excursion> findByPrecioEntre(double precioDe, double precioHasta) {
		List<Excursion> aux = new ArrayList<>();
		for (Excursion ex: lista) {
			if (ex.getPrecioUnitario() >= precioDe && ex.getPrecioUnitario() <= precioHasta)
				aux.add(ex);
		}
		return aux;
	}

	@Override
	public List<Excursion> findByOrigen(String origen) {
	 	List<Excursion> aux = new ArrayList<>();
		for (Excursion ex: lista) {
			if (ex.getOrigen().contains(origen))
				aux.add(ex);
		}
		return aux;
	
		
				
		
	}

	@Override
	public List<Excursion> findByDestino(String destino) {
		List<Excursion> aux = new ArrayList<>();
		for (Excursion ex: lista) {
			if (ex.getDestino().contains(destino))
				aux.add(ex);
		}
		return aux;
	}

	

	
	

}
