package principal.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import principal.modelo.javabean.Producto;

@Repository

public class ProductoDaoImpList implements ProductoDao {

	List<Producto> lista;

	public ProductoDaoImpList() {
		lista = new ArrayList<>();
		cargarDatos();
	}

	private void cargarDatos() {
		lista.add(new Producto(1001, "Camisa blanca de Hombre talla 5", 56, 50, new Date()));
		lista.add(new Producto(1002, "Camisa negra de Mujer talla XS", 89, 75, new Date()));
		lista.add(new Producto(1003, "Pantalon blanco de Hombre talla 50", 80, 150, new Date()));
		lista.add(new Producto(1004, "Camisa verde de Mujer talla 42", 90, 30, new Date()));

	}

	@Override
	public Producto findbyId(long idProducto) {
		for (Producto ele: lista) {
			if (ele.getIdProducto() == idProducto)
				return ele;
		}
		return null;
	
	}

	@Override
	public int insertOne(Producto producto) {
		if(lista.contains(producto))
			return 0;
		return lista.add(producto) ? 1 : 0;
		
	}

	@Override
	public int updateOne(Producto producto) {
		int pos = lista.indexOf(producto);
		if(pos == -1)
			return 0;
		return (lista.set(pos, producto)!= null) ? 1 : 0;
		
		/*if(lista.set(pos, producto)!= null)
			return 1;
		else return 0;*/
		
	}

	@Override
	public int deleteOne(Producto producto) {
		
		return lista.remove(producto) ? 1 : 0;
	}

	@Override
	public int deleteOne(long idProducto) {
		Producto producto = findbyId(idProducto);
		return deleteOne(producto);
	}

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public List<Producto> findbyDescripcionContaining(String cadena) {
		// TODO Auto-generated method stub
		return null;
	}

}
