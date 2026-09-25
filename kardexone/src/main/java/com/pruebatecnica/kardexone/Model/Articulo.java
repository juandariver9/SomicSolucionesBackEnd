package com.pruebatecnica.kardexone.Model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "articulo")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articuloId;

    @NotBlank(message = "El código es obligatorio.")
    @Column(nullable = false, unique = true)
    private String articuloCodigo;

    @NotBlank(message = "El nombre es obligatorio.")
    @Column(nullable = false)
    private String articuloNombre;

    @NotBlank(message = "El laboratorio es obligatorio.")
    @Column(nullable = false)
    private String articuloLaboratorio;

    @NotNull(message = "El saldo es obligatorio.")
    @PositiveOrZero(message = "El saldo no puede ser negativo.")
    @Column(nullable = false)
    private Integer articuloSaldo;

    @NotNull(message = "El costo es obligatorio.")
    @PositiveOrZero(message = "El costo no puede ser negativo.")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal articuloCosto;

    @NotNull(message = "El precio de venta es obligatorio.")
    @PositiveOrZero(message = "El precio de venta no puede ser negativo.")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal articuloPrecioVenta;

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

    public BigDecimal getArticuloCosto() {
        return articuloCosto;
    }

    public void setArticuloCosto(BigDecimal articuloCosto) {
        this.articuloCosto = articuloCosto;
    }

    public BigDecimal getArticuloPrecioVenta() {
        return articuloPrecioVenta;
    }

    public void setArticuloPrecioVenta(BigDecimal articuloPrecioVenta) {
        this.articuloPrecioVenta = articuloPrecioVenta;
    }
}
