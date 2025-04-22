package com.pruebatecnica.kardexone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebatecnica.kardexone.Model.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    Optional<Articulo> findByArticuloCodigo(String articuloCodigo);
}
