package eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eventos.entidades.Usuario;
import eventos.repository.UsuarioRepository;

@Controller
@RequestMapping("/app/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/lista")
	public String listarUsuarios(Model model) {
		List<Usuario> usuarios = usuarioRepository.findAll();
		model.addAttribute("usuarios", usuarios);
		return "listaUsuarios";
	}
}