package restventas.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restventas.dto.PedidoDto;
import restventas.modelo.services.PedidoServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedidos")
public class PedidoRestController {
	
	@Autowired
	private  PedidoServices pse;

    @GetMapping("/{idComercial}")
    public ResponseEntity<List<PedidoDto>> obtenerPedidosPorComercial(@PathVariable int idComercial) {
        List<PedidoDto> pedidos = pse.obtenerPedidosPorComercial(idComercial);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(pedidos); 
    }
}


