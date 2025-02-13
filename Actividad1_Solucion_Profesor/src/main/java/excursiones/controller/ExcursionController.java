package excursiones.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import excursiones.modelo.dao.ExcursionDao;
import excursiones.modelo.javabeans.Excursion;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/excursiones")
public class ExcursionController {
	
	@Autowired
	private ExcursionDao edao;
	
	@PostMapping("/modificar/{idExcursion}")
	public String editarFormEditarPost(Excursion excursion,
				RedirectAttributes ratt) {
		
		if (edao.updateOne(excursion) == 1) 
			ratt.addFlashAttribute("mensaje", "Modificación confirmada");
		else
			ratt.addFlashAttribute("mensaje", "Modificación NOO confirmada");
		
		
		return "redirect:/";
	}
	@GetMapping("/modificar/{idExcursion}")
	public String altaFormAlta(Model model, @PathVariable int idExcursion) {
		Excursion ex = edao.findById(idExcursion);
		model.addAttribute("excursion", ex);
		return "FormEditar";
	}
	
	
	@GetMapping("/alta")
	public String altaFormAlta() {
		return "FormAlta";
	}
	@PostMapping("/alta")
	public String altaFormAltaPost(Excursion excursion,
				RedirectAttributes ratt) {
		
		excursion.setEstado("CREADO");
		excursion.setFechaAlta(new Date());
		if (edao.insertOne(excursion) == 1) 
			ratt.addFlashAttribute("mensaje", "Alta confirmada");
		else
			ratt.addFlashAttribute("mensaje", "Alta NOO confirmada");
		
		
		return "redirect:/";
	}
	@GetMapping("/cancelar/{idExcursion}")
	public String cancelar(@PathVariable int idExcursion, Model model) {
		Excursion ex = edao.findById(idExcursion);
		if (ex != null) {
			ex.setEstado("CANCELADO");
			edao.updateOne(ex);
			model.addAttribute("mensaje", "Estado Modificado con Exito");
		}
		else {
			model.addAttribute("mensaje", "Estado NO modificado");
		}
			
		
		return "forward:/";
	}
	
	@GetMapping("/detalle/{idExcursion}")
	public String terminados(@PathVariable int idExcursion, Model model) {
		model.addAttribute("excursion", edao.findById(idExcursion));
		
		return "VerDetalle";
	}
	
	@GetMapping("/terminados")
	public String terminados(Model model) {
		model.addAttribute("excursionesBuscar", edao.findByTerminados());
		model.addAttribute("tipoListado", "Terminados");
		
		return "listados";
	}
	
	@GetMapping("/creados")
	public String creados(Model model) {
		model.addAttribute("excursionesBuscar", edao.findByActivos());
		model.addAttribute("tipoListado", "Creados");
		
		return "listados";
	}
	
	@GetMapping("/cancelados")
	public String cancelados(Model model) {
		model.addAttribute("excursionesBuscar", edao.findByCancelados());
		model.addAttribute("tipoListado", "Cancelados");
		
		return "listados";
	}
	
	@GetMapping("/destacados")
	public String destacados(Model model) {
		model.addAttribute("excursionesBuscar", edao.findByDestacados());
		model.addAttribute("tipoListado", "Destacados");
		
		return "listados";
	}
	
	@GetMapping("/origen")
	public String origen(@RequestParam String origen, Model model) {
		model.addAttribute("excursionesBuscar", edao.findByOrigen(origen));
		model.addAttribute("tipoListado", "Origen");
		
		return "listados";
	}
	
	@GetMapping("/destino")
	public String destino(@RequestParam String destino, Model model) {
		model.addAttribute("excursionesBuscar", edao.findByDestino(destino));
		model.addAttribute("tipoListado", "Destino");
		
		return "listados";
	}
	
	@GetMapping("/precioMayor")
	public String precioMayor(@RequestParam double precio, Model model) {
		model.addAttribute("excursionesBuscar", edao.findByPrecioMayor(precio));
		model.addAttribute("tipoListado", "Precio Mayor Que");
		
		return "listados";
	}
	@GetMapping("/precioMenor")
	public String precioMenor(@RequestParam double precio, Model model) {
		model.addAttribute("excursionesBuscar", edao.findByPrecioMenor(precio));
		model.addAttribute("tipoListado", "Precio Menor Que");
		
		return "listados";
	}
	
	@GetMapping("/precioEntre")
	public String precioMenor(@RequestParam double precioDe, @RequestParam double precioHasta, Model model) {
		model.addAttribute("excursionesBuscar", edao.findByPrecioEntre(precioDe, precioHasta));
		model.addAttribute("tipoListado", "Precio De Hasta");
		
		return "listados";
	}
	 
	@InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
