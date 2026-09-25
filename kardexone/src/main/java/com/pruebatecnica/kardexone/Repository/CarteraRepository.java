package com.pruebatecnica.kardexone.Repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pruebatecnica.kardexone.Model.Cartera;
import com.pruebatecnica.kardexone.Model.TipoFactura;

public interface CarteraRepository extends JpaRepository<Cartera, Long> {

    /**
     * Suma el valor pendiente de la cartera de un cliente para un tipo de factura.
     * Devuelve null si el cliente no tiene registros.
     */
    @Query("select sum(c.carteraValorPendiente) from Cartera c "
            + "where c.nit.nitId = :nitId and c.factura.facturaTipo = :tipo")
    BigDecimal sumarPendientePorNitYTipo(@Param("nitId") Long nitId, @Param("tipo") TipoFactura tipo);
}
