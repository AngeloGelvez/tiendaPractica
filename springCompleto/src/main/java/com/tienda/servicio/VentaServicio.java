package com.tienda.servicio;

import com.tienda.modelo.VentaModelo;
import com.tienda.repositorio.IProductoRepositorio;
import com.tienda.repositorio.IVentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class VentaServicio {

    @Autowired
    private IVentaRepositorio repoVenta;

    @Autowired
    private IProductoRepositorio repoProducto;

    public void crear(VentaModelo venta) {
        this.repoVenta.save(venta);
    }

    public List<VentaModelo> lista() {
        return this.repoVenta.findAll();
    }

    public void eliminar(Integer id) {
        this.repoVenta.deleteById(id);
    }
}
