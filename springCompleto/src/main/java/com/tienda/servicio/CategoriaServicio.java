package com.tienda.servicio;

import com.tienda.modelo.CategoriaModelo;
import com.tienda.repositorio.ICategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CategoriaServicio {

    @Autowired
    private ICategoriaRepositorio repositorioCatego;

    public void crear(CategoriaModelo categoria) {
        this.repositorioCatego.save(categoria);
    }

    public List<CategoriaModelo> listar() {
        return this.repositorioCatego.findAll();
    }

    public CategoriaModelo categoriaId(Integer id) {
        return repositorioCatego.findById(id).get();
    }

    public void editar(CategoriaModelo categoria) {
        this.repositorioCatego.save(categoria);
    }

    public void eliminar(Integer id) {
        this.repositorioCatego.deleteById(id);
    }
}
