package com.tienda.controlador;

import com.tienda.modelo.CategoriaModelo;
import com.tienda.servicio.CategoriaServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/categoria")
public class CategoriaControlador {

    @Autowired
    private CategoriaServicio servicioCatego;

    @GetMapping("/crear")
    public String crearCategoria(Model model) {

        model.addAttribute("categoria", new CategoriaModelo());
        return "categoria/crear";
    }

    @PostMapping("/crear")
    public String crearCategoriaPost(@Valid CategoriaModelo categoria, BindingResult result, RedirectAttributes flash, Model model)  {
        if (result.hasErrors()) {
            HashMap<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage() + ".");
            });

            model.addAttribute("errores", errores);
            model.addAttribute("categoria", new CategoriaModelo());
            return "categoria/crear";
        }

        try {
            this.servicioCatego.crear(categoria);

            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "La categoria fue creada con exito");
            flash.addFlashAttribute("color", "#53ac59");
            return "redirect:/";
        }catch (Exception exception) {
            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "La categoria no pudo ser creada");
            flash.addFlashAttribute("color", "#FF3833");
            return "redirect:/categoria/crear";
        }
    }

    @GetMapping("/editar")
    public String editar(Model model) {

        model.addAttribute("listaCategoria", servicioCatego.listar());
         return "categoria/editar";
    }

    @GetMapping("/editar/{id}")
    public String editarId(Model model, @PathVariable Integer id) {
        model.addAttribute("categoria", servicioCatego.categoriaId(id));
        return "categoria/editar_id";
    }

    @PostMapping("/editar/{id}")
    public String editar_post(@Valid CategoriaModelo categoria, BindingResult result, RedirectAttributes flash, Model model, @PathVariable Integer id) {

        if (result.hasErrors()) {
            HashMap<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(e -> {
                errores.put(e.getField(), "El campo " + e.getField() + " " + e.getDefaultMessage());
            });
            model.addAttribute("errores", errores);
            model.addAttribute("categoria", categoria);
            return "categoria/editar_id";
        }

        try {
            this.servicioCatego.editar(categoria);

            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "La categoria se actualizo con exito");
            flash.addFlashAttribute("color", "#53ac59");
            return "redirect:/categoria/editar";

        } catch (Exception exception) {
            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "La categoria no pudo ser editada");
            flash.addFlashAttribute("color", "#FF3833");
            return "redirect:/categoria/editar/" + id;
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarId(@PathVariable Integer id, RedirectAttributes flash) {

        try {
            this.servicioCatego.eliminar(id);

            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "La categoria se elimino con exito");
            flash.addFlashAttribute("color", "#53ac59");
            return "redirect:/categoria/editar";

        } catch (Exception exception) {
            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "La categoria no pudo ser eliminada");
            flash.addFlashAttribute("color", "#FF3833");
            return "redirect:/categoria/editar";
        }
    }
}
