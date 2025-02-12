package productosweb.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import productosweb.modelo.dao.ProductoDao;
import productosweb.modelo.entidades.Producto;
import productosweb.modelo.repository.ProductoRepository;

//api rest : ruta + funcion de http: get, Post, put, delete
//funcion que devuelve en el mensaje de salida del http:body-> datos -> JSON

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/productos")
public class ProductoRestController {

	@Autowired
	ProductoDao pdao;
	@Autowired
	private ProductoRepository prepo;

	@GetMapping("/pordescripcion/{cadena}")
	public List<Producto> porCadena(@PathVariable String cadena) {

		return pdao.findbyDescripcionContaining(cadena);
	}
	@GetMapping("/pordescripcion2/{cadena}")
	public List<Producto> porCadenaContaining(@PathVariable String cadena) {

		return prepo.findByDescripcionContaining(cadena);
	}
	@GetMapping("/preciostock/{precio}/{stock}")
	public List<Producto> proPrecioStock(@PathVariable double precio, @PathVariable int stock ) {

		return prepo.findByPrecioGreaterThanAndStockLessThan(precio, stock);
	}


}
