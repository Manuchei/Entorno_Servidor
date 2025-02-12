package eventos.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.entidades.Evento;
import eventos.entidades.Reserva;
import eventos.entidades.Usuario;
import eventos.repository.EventoRepository;
import eventos.repository.ReservaRepository;
import eventos.repository.UsuarioRepository;

@Controller
@RequestMapping("/misReservas")
public class ReservaController {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@GetMapping
	public String listarReservasActivas(Authentication authentication, Model model) {
		String username = authentication.getName();
		Usuario usuario = usuarioRepository.findByUsername(username);

		if (usuario == null) {
			throw new IllegalArgumentException("Usuario no encontrado");
		}

		List<Reserva> reservasActivas = reservaRepository.findByUsuarioAndEvento_FechaInicioAfter(usuario, new Date());

		model.addAttribute("reservas", reservasActivas);
		return "MisReservas";
	}

	@PostMapping("/cancelar/{id}")
	public String cancelarReserva(@PathVariable("id") Integer id, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		String username = authentication.getName();
		Usuario usuario = usuarioRepository.findByUsername(username);

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));

		if (!reserva.getUsuario().equals(usuario)) {
			throw new IllegalArgumentException("No tienes permiso para cancelar esta reserva");
		}

		reservaRepository.delete(reserva);
		redirectAttributes.addFlashAttribute("mensajeExito", "Reserva cancelada con éxito");
		return "redirect:/misReservas";
	}

	@PostMapping("/modificar/{id}")
	public String modificarReserva(@PathVariable("id") Integer id, @RequestParam("cantidad") Integer cantidad,
			Authentication authentication, RedirectAttributes redirectAttributes) {

		String username = authentication.getName();
		Usuario usuario = usuarioRepository.findByUsername(username);

		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));

		if (!reserva.getUsuario().equals(usuario)) {
			throw new IllegalArgumentException("No tienes permiso para modificar esta reserva");
		}

		if (cantidad <= 0 || cantidad > 10) {
			redirectAttributes.addFlashAttribute("mensajeError", "La cantidad debe estar entre 1 y 10");
			return "redirect:/misReservas";
		}

		Evento evento = reserva.getEvento();
		int aforoDisponible = evento.getAforoMaximo() + reserva.getCantidad();

		if (cantidad > aforoDisponible) {
			redirectAttributes.addFlashAttribute("mensajeError", "La cantidad excede el aforo disponible del evento");
			return "redirect:/misReservas";
		}

		evento.setAforoMaximo(aforoDisponible - cantidad);
		reserva.setCantidad(cantidad);
		reserva.setPrecioVenta(evento.getPrecio().multiply(BigDecimal.valueOf(cantidad)));

		reservaRepository.save(reserva);
		eventoRepository.save(evento);

		redirectAttributes.addFlashAttribute("mensajeExito", "Reserva modificada con éxito");
		return "redirect:/misReservas";
	}
}
