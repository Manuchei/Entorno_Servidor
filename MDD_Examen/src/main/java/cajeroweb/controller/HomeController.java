package cajeroweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cajeroweb.modelo.dao.PrestamoDao;
import cajeroweb.modelo.dao.CuentaDao;
import cajeroweb.modelo.entidades.Cuenta;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private CuentaDao cdao;
    
    @Autowired
    private PrestamoDao pdao;  

    @GetMapping({"/", "", "/login"})
    public String login() {
        return "Login";
    }

    @GetMapping("/home")
    public String opciones(Model model, HttpSession session) {
       
        Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");
        
        
        if (cuenta != null) {
            
            model.addAttribute("prestamos", pdao.buscarPorCuenta(cuenta.getIdCuenta()));
        }
        
        return "home";      }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("cuenta");
        session.invalidate();
        return "forward:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam int idCuenta, HttpSession session, Model model) {
        Cuenta cuenta = cdao.buscarUna(idCuenta);
        if (cuenta != null) {
            session.setAttribute("cuenta", cuenta);
            return "redirect:/home";          }
        model.addAttribute("mensaje", "Cuenta no existe");
        return "Login";
    }
}
