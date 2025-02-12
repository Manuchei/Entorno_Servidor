package principal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PruebaController {
	@GetMapping("/pruebas")
	public String parametros(Model model, @RequestParam String profe, @RequestParam int edad) {
		model.addAttribute("profe", profe);
		model.addAttribute("edad", edad);
		return "pruebas";
	}

	@GetMapping("/pruebas/{profe}/{edad}")
	public String verPath(@PathVariable("profe") String profe, @PathVariable int edad, Model model) {
		model.addAttribute("profe", profe);
		model.addAttribute("edad", edad);
		return "pruebas";

	}
}
