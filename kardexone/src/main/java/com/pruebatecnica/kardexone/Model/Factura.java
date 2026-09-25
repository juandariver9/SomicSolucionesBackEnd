package com.pruebatecnica.kardexone.Model;

import java.math.BigDecimal;
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

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facturaId;

    @ManyToOne
    @JoinColumn(name = "facturaNitId", nullable = false)
    private Nit nit;

    /** La asigna el servidor al guardar. */
    @Column(nullable = false)
    private LocalDate facturaFecha;

    /** La calcula el servidor según el plazo del cliente. */
    @Column(nullable = false)
    private LocalDate facturaFechaVencimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoFactura facturaTipo;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal facturaTotal;

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

    public BigDecimal getFacturaTotal() {
        return facturaTotal;
    }

    public void setFacturaTotal(BigDecimal facturaTotal) {
        this.facturaTotal = facturaTotal;
    }
}
