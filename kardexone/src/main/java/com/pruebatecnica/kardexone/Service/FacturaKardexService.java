package com.pruebatecnica.kardexone.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.kardexone.Exception.RecursoNoEncontradoException;
import com.pruebatecnica.kardexone.Exception.ReglaNegocioException;
import com.pruebatecnica.kardexone.Model.Articulo;
import com.pruebatecnica.kardexone.Model.Factura;
import com.pruebatecnica.kardexone.Model.FacturaKardex;
import com.pruebatecnica.kardexone.Model.TipoFactura;
import com.pruebatecnica.kardexone.Repository.ArticuloRepository;
import com.pruebatecnica.kardexone.Repository.FacturaKardexRepository;
import com.pruebatecnica.kardexone.Repository.FacturaRepository;

@Service
@Transactional(readOnly = true)
public class FacturaKardexService {

    private final FacturaKardexRepository facturaKardexRepository;
    private final ArticuloRepository articuloRepository;
    private final FacturaRepository facturaRepository;

    public FacturaKardexService(FacturaKardexRepository facturaKardexRepository,
            ArticuloRepository articuloRepository, FacturaRepository facturaRepository) {
        this.facturaKardexRepository = facturaKardexRepository;
        this.articuloRepository = articuloRepository;
        this.facturaRepository = facturaRepository;
    }

    public List<FacturaKardex> obtenerTodos() {
        return facturaKardexRepository.findAll();
    }

    public FacturaKardex obtenerPorId(Long id) {
        return facturaKardexRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El detalle de factura", id));
    }

    /**
     * Registra una línea de factura y actualiza el saldo del artículo en la misma transacción:
     * las ventas descuentan inventario y las compras lo aumentan.
     * El tipo de movimiento se toma de la factura guardada en base de datos, no del cuerpo de la solicitud.
     */
    @Transactional
    public FacturaKardex guardar(FacturaKardex detalle) {
        if (detalle.getFactura() == null || detalle.getFactura().getFacturaId() == null) {
            throw new ReglaNegocioException("El detalle debe indicar la factura.");
        }
        if (detalle.getArticulo() == null || detalle.getArticulo().getArticuloId() == null) {
            throw new ReglaNegocioException("El detalle debe indicar el artículo.");
        }
        Integer cantidad = detalle.getFkardexcantidad();
        if (cantidad == null || cantidad <= 0) {
            throw new ReglaNegocioException("La cantidad debe ser mayor a cero.");
        }
        BigDecimal precioUnitario = detalle.getFkardexprecio_unitario();
        if (precioUnitario == null || precioUnitario.signum() <= 0) {
            throw new ReglaNegocioException("El precio unitario debe ser mayor a cero.");
        }

        Long facturaId = detalle.getFactura().getFacturaId();
        Factura factura = facturaRepository.findById(facturaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("La factura", facturaId));

        Long articuloId = detalle.getArticulo().getArticuloId();
        Articulo articulo = articuloRepository.findById(articuloId)
                .orElseThrow(() -> new RecursoNoEncontradoException("El artículo", articuloId));

        Integer saldo = articulo.getArticuloSaldo();
        if (saldo == null) {
            throw new ReglaNegocioException("El artículo con ID " + articuloId + " no tiene saldo registrado.");
        }

        TipoFactura tipo = factura.getFacturaTipo();
        if (tipo == TipoFactura.VENTA) {
            if (cantidad > saldo) {
                throw new ReglaNegocioException("Saldo insuficiente para el artículo con ID " + articuloId
                        + ". Disponible: " + saldo + ", solicitado: " + cantidad + ".");
            }
            if (articulo.getArticuloCosto() != null && precioUnitario.compareTo(articulo.getArticuloCosto()) < 0) {
                throw new ReglaNegocioException("El precio de venta no puede ser menor al costo del artículo.");
            }
            articulo.setArticuloSaldo(saldo - cantidad);
        } else if (tipo == TipoFactura.COMPRA) {
            articulo.setArticuloSaldo(saldo + cantidad);
        } else {
            throw new ReglaNegocioException("La factura con ID " + facturaId + " no tiene un tipo válido.");
        }

        BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad)).setScale(2, RoundingMode.HALF_UP);

        detalle.setFkardexId(null);
        detalle.setFactura(factura);
        detalle.setArticulo(articulo);
        detalle.setFkardexsubtotal(subtotal);

        articuloRepository.save(articulo);
        return facturaKardexRepository.save(detalle);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!facturaKardexRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("El detalle de factura", id);
        }
        facturaKardexRepository.deleteById(id);
    }
}
