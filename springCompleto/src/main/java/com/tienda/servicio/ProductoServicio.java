package com.tienda.servicio;

import com.tienda.modelo.ProductoModelo;
import com.tienda.repositorio.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ProductoServicio {

    @Autowired
    private IProductoRepositorio productoRepositorio;

    public void crearProducto(ProductoModelo producto) {
        this.productoRepositorio.save(producto);
    }

    public List<ProductoModelo> listaProducto() {
        return this.productoRepositorio.findAll();
    }

    public ProductoModelo consultaProducto(Integer id) {
        return this.productoRepositorio.findById(id).get();
    }

    public Boolean eliminar(Integer id) {
        if (id == null) {
            return false;
        }else {
            this.productoRepositorio.deleteById(id);
            return true;
        }
    }
}
