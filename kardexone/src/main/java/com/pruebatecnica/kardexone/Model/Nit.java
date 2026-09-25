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
@Table(name = "nit")
public class Nit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nitId;

    @NotBlank(message = "El nombre es obligatorio.")
    @Column(nullable = false)
    private String nitNombre;

    @NotBlank(message = "El documento es obligatorio.")
    @Column(nullable = false, unique = true)
    private String nitDocumento;

    /** Cupo de crédito del cliente. Un cupo de 0 significa que no tiene límite configurado. */
    @NotNull(message = "El cupo es obligatorio.")
    @PositiveOrZero(message = "El cupo no puede ser negativo.")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal nitCupo;

    /** Plazo de pago en días. */
    @NotNull(message = "El plazo es obligatorio.")
    @PositiveOrZero(message = "El plazo no puede ser negativo.")
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

    public BigDecimal getNitCupo() {
        return nitCupo;
    }

    public void setNitCupo(BigDecimal nitCupo) {
        this.nitCupo = nitCupo;
    }

    public Integer getNitPlazo() {
        return nitPlazo;
    }

    public void setNitPlazo(Integer nitPlazo) {
        this.nitPlazo = nitPlazo;
    }
}
