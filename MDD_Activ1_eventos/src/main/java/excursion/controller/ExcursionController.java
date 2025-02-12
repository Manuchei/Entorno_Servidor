package excursion.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import excursion.modelo.dao.ExcursionDao;
import excursion.modelo.javabean.Excursion;

@Controller
@RequestMapping("/excursion")

public class ExcursionController {

	@Autowired
	private ExcursionDao edao;

	/*
	 * En este metodo se sacan en una tabla todas las excuersiones que tiene la S
	 * que indica que son destacadas
	 */
	@GetMapping("/destacadas")
	public String getDestacadas(Model model) {
		List<Excursion> destacadas = edao.findAll().stream().filter(excursion -> "S".equals(excursion.getDestacado()))
				.toList();
		model.addAttribute("excursiones", destacadas);
		return "destacadas";
	}

	/*
	 * En este metodo se sacan las excursiones que en el estado estan marcadas como
	 * creadas
	 */
	@GetMapping("/creadas")
	public String getCreadas(Model model) {
		List<Excursion> creadas = edao.findAll().stream().filter(excursion -> "CREADA".equals(excursion.getEstado()))
				.toList();
		model.addAttribute("excursiones", creadas);
		return "creadas";
	}

	/*
	 * En este metodo se sacan las excursiones que en el estado estan marcadas como
	 * terminadas
	 */
	@GetMapping("/terminadas")
	public String getTerminadas(Model model) {
		List<Excursion> terminadas = edao.findAll().stream()
				.filter(excursion -> "TERMINADA".equals(excursion.getEstado())).toList();
		model.addAttribute("excursiones", terminadas);
		return "terminadas";
	}

	/*
	 * En este metodo se reliza la cancelacion de la excursion que en este caso en
	 * vez de eliminarse solo se modifica el estado a cancelada se hacen por el
	 * metodo get con un set estado y en vez de un delete es un update.
	 */
	@GetMapping("/cancelar/{idExcursion}")
	public String cancelarExcursion(@PathVariable long idExcursion, RedirectAttributes ratt) {
		Excursion excursion = edao.findbyId(idExcursion);
		if (excursion != null)
			excursion.setEstado("CANCELADA");
		if (edao.updateOne(excursion) == 1)
			ratt.addFlashAttribute("mensaje", "Excursion cancelada correctamente");
		else
			ratt.addFlashAttribute("mensaje", "No se pudo cancelar la excursion");
		return "redirect:/";
	}

	/*
	 * Con este metodo se dan de alta las excursiones a traves de la web en vez de
	 * hacerlo a traves de cargar datos en el caso de las altas se hacen a traves
	 * del metodo get y el metodo post, es in insert.
	 */
	@PostMapping("/alta")
	public String procFormAlta(Excursion excursion, RedirectAttributes ratt) {
		if (edao.insertOne(excursion) == 1)
			ratt.addFlashAttribute("mensaje", "Excursion añadida");
		else
			ratt.addFlashAttribute("mensaje", "Excursion no añadida");
		return "redirect:/";
	}

	@GetMapping("/alta")
	public String procAlta() {
		return "formAltaExcursion";
	}

	/*
	 * En este metodo se modifican las excursiones y tambien se realizan por get y
	 * por post y es un update.
	 */
	@PostMapping("/editar/{idExcursion}")
	public String procFormEdicion(@PathVariable long idExcursion, Excursion excursion, RedirectAttributes ratt) {
		excursion.setIdExcursion(idExcursion);
		if (edao.updateOne(excursion) == 1)
			ratt.addFlashAttribute("mensaje", "Excursion modificada correctamente");
		else
			ratt.addFlashAttribute("mensaje", "Imposible modificar excursion");
		return "redirect:/";
	}

	@GetMapping("/editar/{idExcursion}")
	public String procModificar(Model model, @PathVariable long idExcursion) {

		Excursion excursion = edao.findbyId(idExcursion);
		if (excursion == null) {
			model.addAttribute("mensaje", "Excursion no existe, alguien la ha elimindo");
			return "forward:/";
		}
		model.addAttribute("excursion", excursion);

		return "formEdicionExcursion";
	}

	/*
	 * Este metod se utiliza para que en el html se visualicen las excursiones es un
	 * metodo realizado por Get y es un find.
	 */
	@GetMapping("/detalle/{idExcursion}")
	public String verDetalle(Model model, @PathVariable long idExcursion) {
		Excursion excursion = edao.findbyId(idExcursion);
		model.addAttribute("excursion", excursion);
		return "verDetalle";

	}

	// para formatear las horas en los formularios al dar de alta!
	@InitBinder // para formatear las horas en los formularios al dar de alta!
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

	}

}
