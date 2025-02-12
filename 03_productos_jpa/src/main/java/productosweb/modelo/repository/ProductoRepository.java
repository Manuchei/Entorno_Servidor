package productosweb.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import productosweb.modelo.entidades.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

	@Query("select p from Producto p where p.descripcion like ?1 ")
	public List<Producto> findByDescripcionDeproducto(String cadena);
	
	//metodos derivados
	public List<Producto> findByDescripcionContaining(String cadena);
	public List<Producto> findByPrecioGreaterThanAndStockLessThan(double precio, int stock);


}
