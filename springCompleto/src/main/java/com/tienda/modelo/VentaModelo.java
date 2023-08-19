package com.tienda.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "venta")
public class VentaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer id_venta;

    @Column(name = "num_comprobante")
    private long num_comprobante;

    @Column(name = "fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @Column(name = "impuesto")
    private Float impuesto;

    @Column(name = "total")
    private Float total;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoModelo producto;

    public VentaModelo() {
    }

    public VentaModelo(Integer id_venta, long num_comprobante, Date fecha, Float impuesto, Float total, ProductoModelo producto) {
        this.id_venta = id_venta;
        this.num_comprobante = num_comprobante;
        this.fecha = fecha;
        this.impuesto = impuesto;
        this.total = total;
        this.producto = producto;
    }

    public VentaModelo(long num_comprobante, Date fecha, Float impuesto, Float total, ProductoModelo producto) {
        this.num_comprobante = num_comprobante;
        this.fecha = fecha;
        this.impuesto = impuesto;
        this.total = total;
        this.producto = producto;
    }

    public Integer getId_venta() {
        return id_venta;
    }

    public void setId_venta(Integer id_venta) {
        this.id_venta = id_venta;
    }

    public long getNum_comprobante() {
        return num_comprobante;
    }

    public void setNum_comprobante(long num_comprobante) {
        this.num_comprobante = num_comprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Float impuesto) {
        this.impuesto = impuesto;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public ProductoModelo getProducto() {
        return producto;
    }

    public void setProducto(ProductoModelo producto) {
        this.producto = producto;
    }
}
