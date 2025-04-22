package com.pruebatecnica.kardexone.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.kardexone.Model.Cartera;
import com.pruebatecnica.kardexone.Model.Factura;
import com.pruebatecnica.kardexone.Model.Nit;
import com.pruebatecnica.kardexone.Repository.CarteraRepository;
import com.pruebatecnica.kardexone.Repository.FacturaRepository;
import com.pruebatecnica.kardexone.Repository.NitRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private NitRepository nitRepository;

    @Autowired
    private CarteraRepository carteraRepository;

    public List<Factura> obtenerTodos() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> obtenerPorId(Long id) {
        return facturaRepository.findById(id);
    }

    public Factura guardar(Factura factura) {
        // Obtener el NIT
        Nit nit = nitRepository.findById(factura.getNit().getNitId())
            .orElseThrow(() -> new RuntimeException("Cliente (NIT) no encontrado"));

        // Calcular fechas
        LocalDate fechaFactura = LocalDate.now();
        LocalDate fechaVencimiento = fechaFactura.plusDays(nit.getNitPlazo());

        factura.setFacturaFecha(fechaFactura);
        factura.setFacturaFechaVencimiento(fechaVencimiento);

        // Guardar factura
        Factura facturaGuardada = facturaRepository.save(factura);

        // Convertir LocalDate a Date
        Date fechaCartera = Date.from(fechaFactura.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Crear cartera
        Cartera cartera = new Cartera();
        cartera.setCarteraFecha(fechaCartera);
        cartera.setCarteraValor_pendiente(facturaGuardada.getFacturaTotal());
        cartera.setFactura(facturaGuardada);
        cartera.setNit(nit);

        carteraRepository.save(cartera);

        return facturaGuardada;
    }

    public void eliminar(Long id) {
        facturaRepository.deleteById(id);
    }
}
