package principal.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoRestController {
	@GetMapping("/saludo")
	public String saludo() {
		return "HOla me llamo Tomas y soy tu profe";
	}
}
