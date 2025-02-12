package restventas.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restventas.modelo.entities.Comercial;
import restventas.modelo.services.ComercialServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jefecomercial")
public class ComercialRestController {

	@Autowired
	private ComercialServices cse;

	@GetMapping("/totalpedidos")
	public Map<String, Double> getTotalPedidosPorComercial() {
		return cse.obtenerTotalPedidosPorComercial();
	}

	@GetMapping("/bycliente/{idCliente}")
	public List<Comercial> getComercialesPorCliente(@PathVariable int idCliente) {
		return cse.obtenerComercialesPorCliente(idCliente);
	}

	@GetMapping("/sinpedidos")
	public List<Comercial> getComercialesSinPedidos() {
		return cse.obtenerComercialesSinPedidos();
	}

	@DeleteMapping("/{idComercial}")
	public String eliminar(@PathVariable int idComercial) {
		switch (cse.eliminar(idComercial)) {
		case 1:
			return "Comercial borrado correctamente";
		case 0:
			return "Comercial no existe";
		case -1:
			return "Este comercial tiene pedidos y no se puede eliminar";

		default:
			return null;
		}
	}

	@GetMapping("/{idComercial}")
	public Comercial uno(@PathVariable int idComercial) {
		return cse.buscarUno(idComercial);
	}

	@GetMapping("/")
	public List<Comercial> todos() {
		return cse.buscarTodos();

	}

	@PostMapping("/")
	public Comercial alta(@RequestBody Comercial comercial) {
		return cse.alta(comercial);
	}

}
