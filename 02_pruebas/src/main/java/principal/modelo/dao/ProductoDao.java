package principal.modelo.dao;

import java.util.List;

import principal.modelo.javabean.Producto;

public interface ProductoDao {

	Producto findbyId(long idProducto);

	int insertOne(Producto producto);

	int updateOne(Producto producto);

	int deleteOne(Producto producto);

	int deleteOne(long idProducto);

	List<Producto> findAll();

	List<Producto> findbyDescripcionContaining(String cadena);

}
