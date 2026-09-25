package com.pruebatecnica.kardexone.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartera")
public class Cartera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carteraId;

    @ManyToOne
    @JoinColumn(name = "nitId", nullable = false)
    private Nit nit;

    @ManyToOne
    @JoinColumn(name = "facturaId", nullable = false)
    private Factura factura;

    @Column(nullable = false)
    private LocalDate carteraFecha;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal carteraValorPendiente;

    public Long getCarteraId() {
        return carteraId;
    }

    public void setCarteraId(Long carteraId) {
        this.carteraId = carteraId;
    }

    public Nit getNit() {
        return nit;
    }

    public void setNit(Nit nit) {
        this.nit = nit;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public LocalDate getCarteraFecha() {
        return carteraFecha;
    }

    public void setCarteraFecha(LocalDate carteraFecha) {
        this.carteraFecha = carteraFecha;
    }

    // Se conserva el nombre "carteraValor_pendiente" en el JSON porque el frontend lo usa.
    public BigDecimal getCarteraValor_pendiente() {
        return carteraValorPendiente;
    }

    public void setCarteraValor_pendiente(BigDecimal carteraValorPendiente) {
        this.carteraValorPendiente = carteraValorPendiente;
    }
}
