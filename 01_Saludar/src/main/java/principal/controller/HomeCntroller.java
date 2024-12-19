package principal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCntroller {
	
	@GetMapping("/home")
	public String homehomehome() {
		return "inicio";
	}
}


