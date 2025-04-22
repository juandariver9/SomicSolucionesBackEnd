package com.pruebatecnica.kardexone.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.kardexone.Model.Articulo;
import com.pruebatecnica.kardexone.Model.Factura;
import com.pruebatecnica.kardexone.Model.FacturaKardex;
import com.pruebatecnica.kardexone.Model.TipoFactura;
import com.pruebatecnica.kardexone.Repository.ArticuloRepository;
import com.pruebatecnica.kardexone.Repository.FacturaKardexRepository;

@Service
public class FacturaKardexService {

    @Autowired
    private FacturaKardexRepository facturaKardexRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    public List<FacturaKardex> obtenerTodos() {
        return facturaKardexRepository.findAll();
    }

    public Optional<FacturaKardex> obtenerPorId(Long id) {
        return facturaKardexRepository.findById(id);
    }

    public FacturaKardex guardar(FacturaKardex facturaKardex) {
        // Validación de que no haya objetos nulos
        if (facturaKardex.getFactura() == null) {
            throw new RuntimeException("La factura no puede ser nula.");
        }
    
        if (facturaKardex.getArticulo() == null) {
            throw new RuntimeException("El artículo no puede ser nulo.");
        }
    
        Factura factura = facturaKardex.getFactura();
        Articulo articulo = facturaKardex.getArticulo();
    
        // Verificar que el artículo existe en la base de datos
        Optional<Articulo> articuloOpt = articuloRepository.findById(articulo.getArticuloId());
        if (!articuloOpt.isPresent()) {
            throw new RuntimeException("El artículo con ID " + articulo.getArticuloId() + " no existe.");
        }
    
        articulo = articuloOpt.get();  // Asignar el artículo recuperado
    
        // Validación explícita del saldo
        Integer saldo = articulo.getArticuloSaldo();
        if (saldo == null) {
            throw new RuntimeException("El saldo del artículo no puede ser nulo.");
        }
        int saldoActual = saldo;
    
        int cantidad = facturaKardex.getFkardexcantidad();
        double precioUnitario = facturaKardex.getFkardexprecio_unitario();
    
        // Validación: cantidad > 0
        if (cantidad <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a cero.");
        }
    
        // Validación: precio unitario > 0
        if (precioUnitario <= 0) {
            throw new RuntimeException("El precio unitario debe ser mayor a cero.");
        }
    
        // Calcular el subtotal (cantidad * precio unitario)
        double subtotal = cantidad * precioUnitario;
        facturaKardex.setFkardexsubtotal(subtotal);  // Asignar el subtotal
    
        if (factura.getFacturaTipo() == TipoFactura.VENTA) {
            if (saldoActual == 0) {
                throw new RuntimeException("No hay saldo disponible para vender el artículo con ID: " + articulo.getArticuloId());
            }
    
            if (cantidad > saldoActual) {
                throw new RuntimeException("La cantidad solicitada supera el saldo disponible del artículo con ID: " + articulo.getArticuloId());
            }
    
            if (precioUnitario < articulo.getArticuloCosto()) {
                throw new RuntimeException("El precio de venta no puede ser menor al costo del artículo.");
            }
    
            articulo.setArticuloSaldo(saldoActual - cantidad);
    
        } else if (factura.getFacturaTipo() == TipoFactura.COMPRA) {
            articulo.setArticuloSaldo(saldoActual + cantidad);
        }
    
        articuloRepository.save(articulo);
        return facturaKardexRepository.save(facturaKardex);
    }
    
    public void eliminar(Long id) {
        facturaKardexRepository.deleteById(id);
    }
}
