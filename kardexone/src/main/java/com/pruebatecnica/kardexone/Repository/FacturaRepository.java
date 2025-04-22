package com.pruebatecnica.kardexone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebatecnica.kardexone.Model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
