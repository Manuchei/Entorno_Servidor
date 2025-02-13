package cajeroweb.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajeroweb.modelo.dao.CuentaDao;
import cajeroweb.modelo.entidades.Cuenta;
import jakarta.servlet.http.HttpSession;



@Controller
public class HomeController {
	
	@Autowired
	private CuentaDao cdao;
	
	@GetMapping({"inicio"})
	public String inicio() {
		return "inicio";
	}
	@GetMapping({"/", "","/login"})
	public String login() {
		return "Login";
	}
	
	@GetMapping("/home")
	public String opciones() {
		return "home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.removeAttribute("cuenta");
		sesion.invalidate();
		return "forward:/login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam int idCuenta, HttpSession sesion, 
			RedirectAttributes ratt) {
		Cuenta cuenta = cdao.buscarUna(idCuenta);
		if (cuenta != null) {
			sesion.setAttribute("cuenta", cuenta);
			return "redirect:/home";
		}
		ratt.addFlashAttribute("mensaje", "Cuenta no existe");
		
		return "redirect:/login";
	}
	
	

}
