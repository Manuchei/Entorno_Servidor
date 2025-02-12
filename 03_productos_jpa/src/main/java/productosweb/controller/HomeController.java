package productosweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import productosweb.modelo.dao.*;
import productosweb.modelo.dao.*;

@Controller
public class HomeController {
	
	@Autowired
	 private ProductoDao pdao;
	
	@GetMapping("/")
	public String procHome(Model model) {
		
		//List<Producto> lista = pdao.findAll();
		model.addAttribute("productos", pdao.findAll());
		 return "home";
	
		
		
	}
	

}
