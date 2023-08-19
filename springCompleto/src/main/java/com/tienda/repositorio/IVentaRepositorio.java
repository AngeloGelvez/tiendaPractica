package com.tienda.repositorio;

import com.tienda.modelo.VentaModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepositorio extends JpaRepository<VentaModelo, Integer> {
}
