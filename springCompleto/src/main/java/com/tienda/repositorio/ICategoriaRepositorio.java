package com.tienda.repositorio;

import com.tienda.modelo.CategoriaModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepositorio extends JpaRepository<CategoriaModelo, Integer> {


}
