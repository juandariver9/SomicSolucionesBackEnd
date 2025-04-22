package com.pruebatecnica.kardexone.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facturaId;

    @ManyToOne
    @JoinColumn(name = "facturaNitId", nullable = false)
    private Nit nit;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate facturaFecha;

    @Column(nullable = false)
    private LocalDate facturaFechaVencimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoFactura facturaTipo;

    @Column(nullable = false)
    private Double facturaTotal;

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public Nit getNit() {
        return nit;
    }

    public void setNit(Nit nit) {
        this.nit = nit;
    }

    public LocalDate getFacturaFecha() {
        return facturaFecha;
    }

    public void setFacturaFecha(LocalDate facturaFecha) {
        this.facturaFecha = facturaFecha;
    }

    public LocalDate getFacturaFechaVencimiento() {
        return facturaFechaVencimiento;
    }

    public void setFacturaFechaVencimiento(LocalDate facturaFechaVencimiento) {
        this.facturaFechaVencimiento = facturaFechaVencimiento;
    }

    public TipoFactura getFacturaTipo() {
        return facturaTipo;
    }

    public void setFacturaTipo(TipoFactura facturaTipo) {
        this.facturaTipo = facturaTipo;
    }

    public Double getFacturaTotal() {
        return facturaTotal;
    }

    public void setFacturaTotal(Double facturaTotal) {
        this.facturaTotal = facturaTotal;
    }

}
