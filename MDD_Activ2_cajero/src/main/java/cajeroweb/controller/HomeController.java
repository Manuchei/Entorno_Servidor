package cajeroweb.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Movimiento;
import cajeroweb.modelo.dao.MovimientoDao;
import cajeroweb.modelo.repository.CuentaRepository;

import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private CuentaRepository cdao;

	@Autowired
	private MovimientoDao mdao;

	@GetMapping({ "", "/", "/login" })
	public String mostrarFormLogin() {
		return "FormLogin";
	}

	@PostMapping("/login")
	public String procesarFormLogin(@RequestParam int idCuenta, HttpSession sesion, RedirectAttributes ratt) {

		Cuenta cuenta = cdao.findByIdCuenta(idCuenta);

		if (cuenta != null) {
			sesion.setAttribute("cuenta", cuenta);
			ratt.addFlashAttribute("cuenta", cuenta.getIdCuenta());
			return "redirect:/menu";
		} else {
			ratt.addFlashAttribute("mensaje", "Cuenta incorrecta");
			return "redirect:/login";
		}
	}

	@GetMapping("/menu")
	public String mostrarMenu(HttpSession sesion, Model model) {
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		if (cuenta == null) {
			return "redirect:/login";
		}
		model.addAttribute("cuenta", cuenta);
		return "menu";
	}

	@GetMapping("/movimientos")
	public String verMovimientos(HttpSession sesion, Model model) {
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");

		if (cuenta == null) {
			return "redirect:/login";
		}

		List<Movimiento> movimientos = mdao.obtenerMovimientosPorCuenta(cuenta);
		model.addAttribute("movimientos", movimientos);
		model.addAttribute("cuenta", cuenta);
		model.addAttribute("saldo", cuenta.getSaldo());

		return "movimientos";
	}

	@PostMapping("/ingresar")
	public String ingresarDinero(@RequestParam double cantidad, HttpSession sesion, RedirectAttributes ratt) {
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		if (cuenta != null) {
			cuenta.setSaldo(cuenta.getSaldo() + cantidad);
			cdao.save(cuenta);

			Movimiento movimiento = new Movimiento();
			movimiento.setCuenta(cuenta);
			movimiento.setFecha(new Date());
			movimiento.setCantidad(cantidad);
			movimiento.setOperacion("Ingreso");
			mdao.guardarMovimiento(movimiento);

			ratt.addFlashAttribute("mensaje", "Ingreso realizado exitosamente.");
		} else {
			ratt.addFlashAttribute("mensaje", "Error: No se ha encontrado la cuenta.");
		}
		return "redirect:/menu";
	}

	@PostMapping("/extraer")
	public String extraerDinero(@RequestParam double cantidad, HttpSession sesion, RedirectAttributes ratt) {
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		if (cuenta != null) {
			if (cuenta.getSaldo() >= cantidad) {
				cuenta.setSaldo(cuenta.getSaldo() - cantidad);
				cdao.save(cuenta);

				Movimiento movimiento = new Movimiento();
				movimiento.setCuenta(cuenta);
				movimiento.setFecha(new Date());
				movimiento.setCantidad(cantidad);
				movimiento.setOperacion("Extracción");
				mdao.guardarMovimiento(movimiento);

				ratt.addFlashAttribute("mensaje", "Extracción realizada exitosamente.");
			} else {
				ratt.addFlashAttribute("mensaje", "Saldo insuficiente para realizar la extracción.");
			}
		} else {
			ratt.addFlashAttribute("mensaje", "Error: No se ha encontrado la cuenta.");
		}
		return "redirect:/menu";
	}

	@GetMapping("/logout")
	public String cerrarSesion(HttpSession sesion) {
		sesion.removeAttribute("cuenta");
		sesion.invalidate();
		return "forward:/";
	}
}