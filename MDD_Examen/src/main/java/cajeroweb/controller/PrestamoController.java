package cajeroweb.controller;

import cajeroweb.modelo.dao.PrestamoDao;
import cajeroweb.modelo.entidades.Prestamo;
import cajeroweb.modelo.entidades.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.Date;

@Controller
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoDao pdao;

 
    @GetMapping("/listar")
    public String listarPrestamos(Model model, HttpSession session) {
        Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");
        if (cuenta != null) {
            model.addAttribute("prestamos", pdao.buscarPorCuenta(cuenta.getIdCuenta()));
        } else {
            model.addAttribute("mensaje", "No hay una cuenta activa en la sesión.");
        }
        return "homePrestamo"; 
    }

    
    @GetMapping("/nuevo")
    public String nuevoPrestamo() {
        return "FormAltaPrestamo"; 
    }

    
    @PostMapping("/nuevo")
    public String procesarNuevoPrestamo(
            @RequestParam String descripcion,
            @RequestParam double cantidad,
            @RequestParam double tasaInteresAnual,
            @RequestParam int plazoMeses,
            @RequestParam String tipoCuota,
            HttpSession session,
            Model model) {

        Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");

        if (cuenta != null) {
            Prestamo prestamo = new Prestamo();
            prestamo.setDescripcion(descripcion);
            prestamo.setCantidad(cantidad);
            prestamo.setTasaInteresAnual(tasaInteresAnual);
            prestamo.setPlazoMeses(plazoMeses);
            prestamo.setTipoCuota(tipoCuota);
            prestamo.setCuenta(cuenta);
            prestamo.setFechaSolicitud(new Date());
            prestamo.setEstado("Pendiente"); 

            pdao.altaPrestamo(prestamo);
            model.addAttribute("mensaje", "Préstamo creado exitosamente.");
        } else {
            model.addAttribute("mensaje", "No hay una cuenta activa en la sesión.");
        }

        return "redirect:/prestamos/listar"; 
    }


    @GetMapping("/eliminar/{idPrestamo}")
    public String eliminarPrestamo(@PathVariable int idPrestamo, Model model) {
        pdao.eliminarPrestamo(idPrestamo);
        model.addAttribute("mensaje", "Préstamo eliminado exitosamente.");
        return "redirect:/prestamos/listar";
    }
}
