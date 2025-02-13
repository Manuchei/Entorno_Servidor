package principal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import principal.modelo.dao.ProductoDao;
import principal.modelo.dao.ProductoDaoImplList;
import principal.modelo.javabean.Producto;

@Controller
public class HomeController {
	
	@Autowired
	private ProductoDao pdao;
	
	
	@GetMapping("/")
	public String procHome(Model model) {
		
	//	List<Producto> lista = pdao.findAll();
		model.addAttribute("productos", pdao.findAll());
		return "home";
		
		
		
		
		
	}

}
