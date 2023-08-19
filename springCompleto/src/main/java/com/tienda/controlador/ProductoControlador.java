package com.tienda.controlador;

import com.tienda.modelo.ProductoModelo;
import com.tienda.servicio.CategoriaServicio;
import com.tienda.servicio.ProductoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Controller
@RequestMapping("/producto")
public class ProductoControlador {

    @Autowired
    private CategoriaServicio servicioCatego;

    @Autowired
    private ProductoServicio servicioProdu;

    @GetMapping("/crear")
    public String crearProducto(Model model) {

        model.addAttribute("categorias", this.servicioCatego.listar());
        model.addAttribute("producto", new ProductoModelo());
        return "producto/crear";
    }

    @PostMapping("/crear")
    public String crearProducto_post(@Valid ProductoModelo miProducto, BindingResult result, RedirectAttributes flash, @RequestParam("subirImagen") MultipartFile imagen, Model model) {

        if (result.hasErrors()) {
            HashMap<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage() + ".");
            });

            model.addAttribute("errores", errores);
            model.addAttribute("categorias", this.servicioCatego.listar());
            model.addAttribute("producto", new ProductoModelo());
            return "producto/crear";
        }

        //CONFIGURACION DE LA IMAGEN
        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/imagenesP");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] byteImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, byteImg);

                miProducto.setImagen(imagen.getOriginalFilename());
            } catch (IOException e) {
                flash.addFlashAttribute("accesoTrue", "fade show");
                flash.addFlashAttribute("mensaje", "Error en la subida de imagen");
                flash.addFlashAttribute("color", "#FF3833");
                return "redirect:/producto/crear";
            }
        }

        try{
            this.servicioProdu.crearProducto(miProducto);

            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "El producto fue creado con exito");
            flash.addFlashAttribute("color", "#53ac59");
            return "redirect:/";
        }catch (Exception e) {
            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "El producto no pudo ser creado");
            flash.addFlashAttribute("color", "#FF3833");
            return "redirect:/producto/crear";
        }
    }

    @GetMapping("/consultar/{id}")
    public String consultar(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", this.servicioProdu.consultaProducto(id));
        return "producto/consultar";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        model.addAttribute("categorias", this.servicioCatego.listar());
        model.addAttribute("producto", this.servicioProdu.consultaProducto(id));
        return "producto/editar";
    }

    @PostMapping("/editar/{id}")
    public String editar_post(@Valid ProductoModelo miProducto, BindingResult result, RedirectAttributes flash, @RequestParam("subirImagen") MultipartFile imagen, Model model, @PathVariable Integer id) {
        if (result.hasErrors()) {
            HashMap<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage() + ".");
            });

            model.addAttribute("errores", errores);
            model.addAttribute("categorias", this.servicioCatego.listar());
            model.addAttribute("producto", this.servicioProdu.consultaProducto(id));
            return "producto/editar";
        }

        //CONFIGURACION DE LA IMAGEN
        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/imagenesP");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] byteImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, byteImg);

                miProducto.setImagen(imagen.getOriginalFilename());
            } catch (IOException e) {
                flash.addFlashAttribute("accesoTrue", "fade show");
                flash.addFlashAttribute("mensaje", "Error en la subida de imagen");
                flash.addFlashAttribute("color", "#FF3833");
                return "redirect:/producto/editar/" + id;
            }
        }

        try{
            this.servicioProdu.crearProducto(miProducto);

            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "El producto fue editado con exito");
            flash.addFlashAttribute("color", "#53ac59");
            return "redirect:/";
        }catch (Exception e) {
            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "El producto no pudo ser editado");
            flash.addFlashAttribute("color", "#FF3833");
            return "redirect:/producto/editar/" + id;
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes flash) {

        if (this.servicioProdu.eliminar(id)) {
            this.servicioProdu.eliminar(id);

            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "El producto fue eliminado con exito");
            flash.addFlashAttribute("color", "#53ac59");
            return "redirect:/";
        } else {
            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "El producto no pudo ser eliminado");
            flash.addFlashAttribute("color", "#FF3833");
            return "redirect:/consultar/editar/" + id;
        }
    }
}
