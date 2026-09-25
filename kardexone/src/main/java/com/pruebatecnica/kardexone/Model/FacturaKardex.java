package com.pruebatecnica.kardexone.Model;

import java.math.BigDecimal;

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

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal fkardexprecio_unitario;

    /** Lo calcula el servidor: cantidad x precio unitario. */
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal fkardexsubtotal;

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

    public BigDecimal getFkardexprecio_unitario() {
        return fkardexprecio_unitario;
    }

    public void setFkardexprecio_unitario(BigDecimal fkardexprecio_unitario) {
        this.fkardexprecio_unitario = fkardexprecio_unitario;
    }

    public BigDecimal getFkardexsubtotal() {
        return fkardexsubtotal;
    }

    public void setFkardexsubtotal(BigDecimal fkardexsubtotal) {
        this.fkardexsubtotal = fkardexsubtotal;
    }
}
