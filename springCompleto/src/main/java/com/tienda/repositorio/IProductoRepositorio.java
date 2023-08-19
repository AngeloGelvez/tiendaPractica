package com.tienda.repositorio;

import com.tienda.modelo.ProductoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepositorio extends JpaRepository<ProductoModelo, Integer> {
    //CREAR BUSQUEDAS PERSONALIZADAS
}
