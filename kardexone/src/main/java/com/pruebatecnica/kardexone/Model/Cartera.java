package com.pruebatecnica.kardexone.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
    @Temporal(TemporalType.DATE)
    private Date carteraFecha;

    @Column(nullable = false)
    private Double carteraValorPendiente;

    // Getters y Setters
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

    public Date getCarteraFecha() {
        return carteraFecha;
    }

    public void setCarteraFecha(Date carteraFecha) {
        this.carteraFecha = carteraFecha;
    }

    public Double getCarteraValor_pendiente() {
        return carteraValorPendiente;
    }

    public void setCarteraValor_pendiente(Double carteraValor_pendiente) {
        this.carteraValorPendiente = carteraValor_pendiente;
    }
}
