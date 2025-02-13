package excursiones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import excursiones.modelo.dao.ExcursionDao;


@Controller
public class HomeController {
	
	@Autowired
	private ExcursionDao edao;
	
	
	
	@GetMapping("/")
	public String inicio(Model model) {
		model.addAttribute("excursiones", edao.findAll());
		return "home";
	}
	

}
