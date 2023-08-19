package com.tienda.controlador;

import com.tienda.servicio.CategoriaServicio;
import com.tienda.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeControlador {

    @Autowired
    private CategoriaServicio serviceCatego;

    @Autowired
    private ProductoServicio servicioProdu;

    @GetMapping("")
    public String home(Model model) {
        int numeroAleatorio = (int)Math.round(Math.random() * 3);

        if (numeroAleatorio == 0) {
            numeroAleatorio = 1;
        }

        model.addAttribute("num", numeroAleatorio);
        return "home.html";
    }

    @ModelAttribute
    public void utilidades(Model model) {
        model.addAttribute("categorias", this.serviceCatego.listar());
        model.addAttribute("listaProdu", this.servicioProdu.listaProducto());
    }
}
