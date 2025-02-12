package eventos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import eventos.entidades.Evento;
import eventos.entidades.Tipo;
import eventos.repository.EventoRepository;
import eventos.repository.TipoRepository;

@Controller
@RequestMapping("/app/evento")
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private TipoRepository tipoRepository;

	@GetMapping("/alta")
	public String mostrarFormularioAlta(Model model) {
		model.addAttribute("evento", new Evento());
		model.addAttribute("tipos", tipoRepository.findAll());
		return "AltaEvento";
	}

	@PostMapping("/alta")
	public String procesarAltaEvento(Evento evento) {
		evento.setEstado("ACEPTADO");
		if (evento.getDestacado() == null || evento.getDestacado().isEmpty()) {
			evento.setDestacado("N");
		}
		eventoRepository.save(evento);
		return "redirect:/home";
	}

	@PostMapping("/editar/{id}")
	public String actualizarEvento(@PathVariable("id") Integer id, @ModelAttribute Evento evento) {
		System.out.println("Estado recibido: " + evento.getEstado());
		System.out.println("Tipo recibido: " + (evento.getTipo() != null ? evento.getTipo().getIdTipo() : "NULL"));

		Evento eventoExistente = eventoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));

		eventoExistente.setNombre(evento.getNombre());
		eventoExistente.setDescripcion(evento.getDescripcion());
		eventoExistente.setFechaInicio(evento.getFechaInicio());
		eventoExistente.setEstado(evento.getEstado());
		eventoExistente.setDestacado(evento.getDestacado());
		eventoExistente.setDireccion(evento.getDireccion());
		eventoExistente.setDuracion(evento.getDuracion());
		eventoExistente.setAforoMaximo(evento.getAforoMaximo());
		eventoExistente.setMinimoAsistencia(evento.getMinimoAsistencia());
		eventoExistente.setPrecio(evento.getPrecio());

		if (evento.getTipo() != null && evento.getTipo().getIdTipo() != 0) {
			Tipo tipo = tipoRepository.findById(evento.getTipo().getIdTipo())
					.orElseThrow(() -> new IllegalArgumentException("Tipo no válido"));
			eventoExistente.setTipo(tipo);
		} else {
			throw new IllegalArgumentException("Tipo es obligatorio");
		}

		eventoRepository.save(eventoExistente);

		return "redirect:/home";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
		Evento evento = eventoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
		model.addAttribute("evento", evento);
		model.addAttribute("tipos", tipoRepository.findAll()); // Lista de tipos
		return "ActualizarEvento";
	}

	@GetMapping("/filtrar")
	public String filtrarEventos(@RequestParam("estado") String estado, Model model) {
		List<Evento> eventos;

		switch (estado) {
		case "destacados":
			eventos = eventoRepository.findByDestacado("S");
			break;
		case "cancelados":
			eventos = eventoRepository.findByEstado("CANCELADO");
			break;
		case "terminados":
			eventos = eventoRepository.findByEstado("TERMINADO");
			break;
		case "activos":
			eventos = eventoRepository.findByEstado("ACEPTADO");
			break;
		default:
			eventos = eventoRepository.findAll();
			break;
		}

		model.addAttribute("eventos", eventos);
		model.addAttribute("estadoSeleccionado", estado);
		return "listaEventos";
	}

	@GetMapping("/lista")
	public String listarEventos(Model model) {
		List<Evento> eventos = eventoRepository.findAll();
		model.addAttribute("eventos", eventos);
		return "listaEventos";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarEvento(@PathVariable("id") Integer id) {
		if (eventoRepository.existsById(id)) {
			eventoRepository.deleteById(id);

		}
		return "redirect:/home";
	}

	@GetMapping("/cancelar/{id}")
	public String cancelarEvento(@PathVariable("id") Integer id) {
		Optional<Evento> optionalEvento = eventoRepository.findById(id);
		if (optionalEvento.isPresent()) {
			Evento evento = optionalEvento.get();
			evento.setEstado("Cancelado");
			eventoRepository.save(evento);

		}
		return "redirect:/home";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
