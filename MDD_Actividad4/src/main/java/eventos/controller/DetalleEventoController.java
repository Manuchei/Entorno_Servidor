package eventos.controller;

import java.math.BigDecimal;

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
@RequestMapping("/detalle")
public class DetalleEventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("/{id}/reservar")
	public String procesarReserva(@PathVariable("id") Integer id, @RequestParam("cantidad") Integer cantidad,
			@RequestParam(value = "observaciones", required = false) String observaciones,
			Authentication authentication, RedirectAttributes redirectAttributes) {

		Evento evento = eventoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));

		if (cantidad <= 0 || cantidad > 10) {
			redirectAttributes.addFlashAttribute("mensajeError", "La cantidad debe estar entre 1 y 10.");
			return "redirect:/detalle/" + id;
		}

		if (cantidad > evento.getAforoMaximo()) {
			redirectAttributes.addFlashAttribute("mensajeError",
					"La cantidad seleccionada supera el aforo máximo disponible.");
			return "redirect:/detalle/" + id;
		}

		evento.setAforoMaximo(evento.getAforoMaximo() - cantidad);
		eventoRepository.save(evento);

		String username = authentication.getName();
		Usuario usuario = usuarioRepository.findByUsername(username);
		if (usuario == null) {
			throw new IllegalArgumentException("Usuario no encontrado");
		}

		Reserva reserva = new Reserva();
		reserva.setCantidad(cantidad);
		reserva.setObservaciones(observaciones);
		reserva.setPrecioVenta(evento.getPrecio().multiply(BigDecimal.valueOf(cantidad)));
		reserva.setEvento(evento);
		reserva.setUsuario(usuario);
		reservaRepository.save(reserva);

		redirectAttributes.addFlashAttribute("mensajeExito",
				"Reserva realizada con éxito. Plazas reservadas: " + cantidad);
		return "redirect:/detalle/" + id;
	}

	@GetMapping("/{id}")
	public String mostrarDetalleEvento(@PathVariable("id") Integer id, Model model) {
		Evento evento = eventoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
		model.addAttribute("evento", evento);
		return "DetalleEvento";
	}
}
