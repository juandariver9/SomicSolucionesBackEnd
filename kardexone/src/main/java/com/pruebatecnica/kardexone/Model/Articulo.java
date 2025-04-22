package com.pruebatecnica.kardexone.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulo")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articuloId;

    @Column(nullable = false, unique = true)
    private String articuloCodigo;

    @Column(nullable = false)
    private String articuloNombre;

    @Column(nullable = false)
    private String articuloLaboratorio;

    @Column(nullable = false)
    private Integer articuloSaldo;

    @Column(nullable = false)
    private Double articuloCosto;

    @Column(nullable = false)
    private Double articuloPrecioVenta;

    // Getters y Setters
    public Long getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Long articuloId) {
        this.articuloId = articuloId;
    }

    public String getArticuloCodigo() {
        return articuloCodigo;
    }

    public void setArticuloCodigo(String articuloCodigo) {
        this.articuloCodigo = articuloCodigo;
    }

    public String getArticuloNombre() {
        return articuloNombre;
    }

    public void setArticuloNombre(String articuloNombre) {
        this.articuloNombre = articuloNombre;
    }

    public String getArticuloLaboratorio() {
        return articuloLaboratorio;
    }

    public void setArticuloLaboratorio(String articuloLaboratorio) {
        this.articuloLaboratorio = articuloLaboratorio;
    }

    public Integer getArticuloSaldo() {
        return articuloSaldo;
    }

    public void setArticuloSaldo(Integer articuloSaldo) {
        this.articuloSaldo = articuloSaldo;
    }

    public Double getArticuloCosto() {
        return articuloCosto;
    }

    public void setArticuloCosto(Double articuloCosto) {
        this.articuloCosto = articuloCosto;
    }

    public Double getArticuloPrecioVenta() {
        return articuloPrecioVenta;
    }

    public void setArticuloPrecioVenta(Double articuloPrecioVenta) {
        this.articuloPrecioVenta =  articuloPrecioVenta;
    }

}
