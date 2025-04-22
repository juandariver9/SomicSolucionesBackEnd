package com.pruebatecnica.kardexone.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "facturakardex")
public class FacturaKardex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fkardexId;

    @ManyToOne
    @JoinColumn(name = "facturaId", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "articuloId", nullable = false)
    private Articulo articulo;

    @Column(nullable = false)
    private Integer fkardexcantidad;

    @Column(nullable = false)
    private Double fkardexprecio_unitario;

    @Column(nullable = false)
    private Double fkardexsubtotal;

    // Getters y Setters
    public Long getFkardexId() {
        return fkardexId;
    }

    public void setFkardexId(Long fkardexId) {
        this.fkardexId = fkardexId;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Integer getFkardexcantidad() {
        return fkardexcantidad;
    }

    public void setFkardexcantidad(Integer fkardexcantidad) {
        this.fkardexcantidad = fkardexcantidad;
    }

    public Double getFkardexprecio_unitario() {
        return fkardexprecio_unitario;
    }

    public void setFkardexprecio_unitario(Double fkardexprecio_unitario) {
        this.fkardexprecio_unitario = fkardexprecio_unitario;
    }

    public Double getFkardexsubtotal() {
        return fkardexsubtotal;
    }

    public void setFkardexsubtotal(Double fkardexsubtotal) {
        this.fkardexsubtotal = fkardexsubtotal;
    }
}
