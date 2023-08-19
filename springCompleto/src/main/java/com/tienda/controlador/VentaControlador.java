package com.tienda.controlador;

import com.tienda.modelo.ProductoModelo;
import com.tienda.modelo.VentaModelo;
import com.tienda.servicio.ProductoServicio;
import com.tienda.servicio.VentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/venta")
public class VentaControlador {

    @Autowired
    private ProductoServicio productoServi;

    @Autowired
    private VentaServicio ventaServi;

    @GetMapping("/{id}")
    public String crearRecibo(Model model, @PathVariable Integer id) {

        String comprobante = "";
        ProductoModelo miProducto = this.productoServi.consultaProducto(id);
        float impuesto = (float) (this.productoServi.consultaProducto(id).getPrecio() * 0.05);
        float total =  miProducto.getPrecio() + impuesto;

        for (int i = 0; i < 9; i++) {
            comprobante = comprobante + Math.round(Math.random()*9);
        }

        model.addAttribute("venta", new VentaModelo(Long.parseLong(comprobante), new Date(), impuesto, total, miProducto));
        model.addAttribute("producto", miProducto);
        return "venta/crear.html";
    }

    @PostMapping("/")
    public String crearRecibo_post(Model model, VentaModelo venta, RedirectAttributes flash) {

        try{
            this.ventaServi.crear(venta);

            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "Felicidades, la compra fue realizada con exito!!");
            flash.addFlashAttribute("color", "#53ac59");
            return "redirect:/";
        }catch (Exception e) {
            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "Error, la compra no pudo ser realizada");
            flash.addFlashAttribute("color", "#FF3833");
            return "redirect:/";
        }
    }

    @GetMapping("/carrito")
    public String consulta(Model model) {
        model.addAttribute("ventas", this.ventaServi.lista());
        return "venta/consulta";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(Model model, @PathVariable Integer id, RedirectAttributes flash) {

        try{
            this.ventaServi.eliminar(id);

            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "La compra fue cancelada con exito!!");
            flash.addFlashAttribute("color", "#53ac59");
            return "redirect:/venta/carrito";
        }catch (Exception e) {
            flash.addFlashAttribute("accesoTrue", "fade show");
            flash.addFlashAttribute("mensaje", "Error, la compra no pudo ser cancelada");
            flash.addFlashAttribute("color", "#FF3833");
            return "redirect:/venta/carrito";
        }
    }
}
