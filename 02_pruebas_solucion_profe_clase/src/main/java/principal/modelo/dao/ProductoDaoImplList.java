package principal.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import principal.modelo.javabean.Producto;
 @Repository
public class ProductoDaoImplList implements ProductoDao{
	private List<Producto> lista;
	
	public ProductoDaoImplList() {
		lista = new ArrayList<>();
		cargarDatos();
	}
	
	private void cargarDatos() {
		lista.add(new Producto(1001, "Camisa blanca de Hombre talla 5", 56, 50, new Date()));
		lista.add(new Producto(1002, "Camisa negra de Mujer talla XS", 89, 75, new Date()));
		lista.add(new Producto(1003, "Pantalon blanca de Hombre talla 50", 80, 150, new Date()));
		lista.add(new Producto(1004, "Pantalon verde de Mujer talla 42", 90, 30, new Date()));
		
	}

	@Override
	public Producto findById(long idProducto) {
		for (Producto ele: lista) {
			if (ele.getIdProducto() == idProducto)
				return ele;
		}
		return null;
		
		
	}

	@Override
	public int insertOne(Producto producto) {
		if (lista.contains(producto))
			return 0;
		return lista.add(producto) ? 1 : 0;
	}

	@Override
	public int updateOne(Producto producto) {
		int pos = lista.indexOf(producto);
		
		if (pos == -1)
			return 0;
		return (lista.set(pos, producto) != null) ? 1 : 0;
		/*
		if (lista.set(pos, producto) != null)
			return 1;
		else
			return 0;
		*/
	}

	@Override
	public int deleteOne(Producto producto) {
		// TODO Auto-generated method stub
		return lista.remove(producto) ? 1 : 0;
	}

	@Override
	public int deleteOne(long idProducto) {
		Producto producto = findById(idProducto);
		return deleteOne(producto);
	}

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public List<Producto> findByDescripcionContaining(String cadena) {
		// TODO Auto-generated method stub
		return null;
	}

}
