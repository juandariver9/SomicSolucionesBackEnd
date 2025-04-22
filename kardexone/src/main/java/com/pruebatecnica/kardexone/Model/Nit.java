package com.pruebatecnica.kardexone.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nit")
public class Nit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nitId;

    @Column(nullable = false)
    private String nitNombre;

    @Column(nullable = false, unique = true)
    private String nitDocumento;

    @Column(nullable = false)
    private Double nitCupo;

    @Column(nullable = false)
    private Integer nitPlazo;

    public Long getNitId() {
        return nitId;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public String getNitNombre() {
        return nitNombre;
    }

    public void setNitNombre(String nitNombre) {
        this.nitNombre = nitNombre;
    }

    public String getNitDocumento() {
        return nitDocumento;
    }

    public void setNitDocumento(String nitDocumento) {
        this.nitDocumento = nitDocumento;
    }

    public Double getNitCupo() {
        return nitCupo;
    }

    public void setNitCupo(Double nitCupo) {
        this.nitCupo = nitCupo;
    }

    public Integer getNitPlazo() {
        return nitPlazo;
    }

    public void setNitPlazo(Integer nitPlazo) {
        this.nitPlazo = nitPlazo;
    }
}
