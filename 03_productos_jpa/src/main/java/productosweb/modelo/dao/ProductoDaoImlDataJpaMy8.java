package productosweb.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import productosweb.modelo.entidades.Producto;
import productosweb.modelo.repository.ProductoRepository;

@Repository
public class ProductoDaoImlDataJpaMy8 implements ProductoDao {

	@Autowired
	private ProductoRepository prepo;

	@Override
	public Producto findbyId(long idProducto) {
		// TODO Auto-generated method stub
		return prepo.findById(idProducto).orElse(null);
	}

	@Override
	public int insertOne(Producto producto) {
		try {
			prepo.save(producto);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		// return (prepo.save(producto) != null) ? : 0;
	}

	@Override
	public int updateOne(Producto producto) {
		try {
			if (prepo.existsById(producto.getIdProducto())) {
				prepo.save(producto);
				return 1;
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return 0;
	}

	@Override
	public int deleteOne(Producto producto) {
		try {
			if (prepo.existsById(producto.getIdProducto())) {
				prepo.deleteById(producto.getIdProducto());
				return 1;
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return 0;
	}

	@Override
	public int deleteOne(long idProducto) {
		try {
			if (prepo.existsById(idProducto)) {
				prepo.deleteById(idProducto);
				return 1;
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return 0;
	}

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return prepo.findAll();
	}

	@Override
	public List<Producto> findbyDescripcionContaining(String cadena) {
		// TODO Auto-generated method stub
		return prepo.findByDescripcionDeproducto("%" + cadena + "%");
	}

}
