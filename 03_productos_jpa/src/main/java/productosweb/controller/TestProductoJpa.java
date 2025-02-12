package productosweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import productosweb.modelo.entidades.Producto;
import productosweb.modelo.repository.ProductoRepository;

@RestController
public class TestProductoJpa {
	
	@Autowired
	private ProductoRepository prepo;
	
	@GetMapping("/apirest/todos")
	
	public List<Producto> todos(){

		return prepo.findAll();
		}

}
