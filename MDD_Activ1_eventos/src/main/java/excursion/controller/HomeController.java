package excursion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import excursion.modelo.dao.ExcursionDao;

@Controller
public class HomeController {

	@Autowired
	private ExcursionDao edao;

	@GetMapping("/")
	public String procHome(Model model) {
		model.addAttribute("excursiones", edao.findAll());
		return "home";
	}

}
