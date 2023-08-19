package com.tienda.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "producto")
public class ProductoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = true)
    private Integer id_producto;

    @Column(name = "nombre")
    @NotEmpty(message = "esta vacio")
    private String nombre;

    @Column(name = "descripcion")
    @NotEmpty(message = "se encuentra vacio")
    private String descipcion;

    @Column(name = "precio")
    @NotNull(message = "esta vacio")
    private Float precio;

    @Column(name = "stock")
    @NotNull(message = "esta vacio")
    private Integer stock;

    @Column(name = "imagen")
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaModelo categoria;

    public ProductoModelo(Integer id_producto, String nombre, String descipcion, Float precio, Integer stock, String imagen, CategoriaModelo categoria) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descipcion = descipcion;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    public ProductoModelo() {
    }

    public ProductoModelo(String nombre, String descipcion, Float precio, Integer stock, String imagen, CategoriaModelo categoria) {
        this.nombre = nombre;
        this.descipcion = descipcion;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public CategoriaModelo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModelo categoria) {
        this.categoria = categoria;
    }
}
