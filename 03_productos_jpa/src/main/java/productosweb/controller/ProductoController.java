package productosweb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import productosweb.modelo.dao.*;
import productosweb.modelo.entidades.Producto;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoDao pdao;
	
	@PostMapping("/editar/{idProducto}")
	public String procFormEdicion(@PathVariable long idProducto, Producto producto,
			RedirectAttributes ratt) {
		producto.setIdProducto(idProducto);
		if(pdao.updateOne(producto)== 1)
			ratt.addFlashAttribute("mensaje", "Producto modificado correctamente");
		else
			ratt.addFlashAttribute("mensaje", "Producto nooo modificado");
			
		return "redirect:/"; //para la tarea, quema los datos y solo sobrevive el rat7t
	}

	@GetMapping("/editar/{idProducto}")
	public String procModificar(Model model, @PathVariable long idProducto) {

		Producto producto = pdao.findbyId(idProducto);
		if (producto == null) {
			model.addAttribute("mensaje", "Producto no existe, alguien lo ha elimindo");
			return "forward:/";
		}
		model.addAttribute("producto", producto);

		return "formEdicionProducto";
	}

	@PostMapping("/alta")
	public String procFormAlta(Producto producto, RedirectAttributes ratt) {
		// System.out.println(producto);
		if (pdao.insertOne(producto) == 1)
			ratt.addFlashAttribute("mensaje", "Alta realizada");
		else
			ratt.addFlashAttribute("mensaje", "Alta NOO realizada");

		return "redirect:/";

	}

	@GetMapping("/alta")
	public String procAlta() {

		return "formAltaProducto";
	}

	@GetMapping("/eliminar/{idProducto}")
	public String eliminar(Model model, @PathVariable long idProducto) {

		int filas = pdao.deleteOne(idProducto);
		if (filas == 1)
			model.addAttribute("mensaje", "Producto eliminado");
		else
			model.addAttribute("mensaje", "Producto NOO eliminado");

		return "forward:/";
	}

	@GetMapping("/detalle/{idProducto}")
	public String verDetalle(Model model, @PathVariable long idProducto) {

		Producto producto = pdao.findbyId(idProducto);

		model.addAttribute("producto", producto);
		return "VerDetalle";
	}
	@InitBinder //para formatear las horas en los formularios al dar de alta!
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	
	}

}
