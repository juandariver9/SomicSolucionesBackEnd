package com.pruebatecnica.kardexone.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pruebatecnica.kardexone.Exception.RecursoNoEncontradoException;
import com.pruebatecnica.kardexone.Exception.ReglaNegocioException;
import com.pruebatecnica.kardexone.Model.Articulo;
import com.pruebatecnica.kardexone.Model.Factura;
import com.pruebatecnica.kardexone.Model.FacturaKardex;
import com.pruebatecnica.kardexone.Model.TipoFactura;
import com.pruebatecnica.kardexone.Repository.ArticuloRepository;
import com.pruebatecnica.kardexone.Repository.FacturaKardexRepository;
import com.pruebatecnica.kardexone.Repository.FacturaRepository;

@ExtendWith(MockitoExtension.class)
class FacturaKardexServiceTest {

    @Mock
    private FacturaKardexRepository facturaKardexRepository;

    @Mock
    private ArticuloRepository articuloRepository;

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaKardexService service;

    private Articulo articulo;

    @BeforeEach
    void setUp() {
        articulo = new Articulo();
        articulo.setArticuloId(10L);
        articulo.setArticuloSaldo(20);
        articulo.setArticuloCosto(new BigDecimal("1000.00"));
        articulo.setArticuloPrecioVenta(new BigDecimal("1500.00"));
    }

    private Factura factura(TipoFactura tipo) {
        Factura factura = new Factura();
        factura.setFacturaId(1L);
        factura.setFacturaTipo(tipo);
        return factura;
    }

    private FacturaKardex detalle(int cantidad, String precio) {
        Factura refFactura = new Factura();
        refFactura.setFacturaId(1L);
        Articulo refArticulo = new Articulo();
        refArticulo.setArticuloId(10L);

        FacturaKardex detalle = new FacturaKardex();
        detalle.setFactura(refFactura);
        detalle.setArticulo(refArticulo);
        detalle.setFkardexcantidad(cantidad);
        detalle.setFkardexprecio_unitario(new BigDecimal(precio));
        return detalle;
    }

    private void prepararRepositorios(TipoFactura tipo) {
        when(facturaRepository.findById(1L)).thenReturn(Optional.of(factura(tipo)));
        when(articuloRepository.findById(10L)).thenReturn(Optional.of(articulo));
    }

    @Test
    void ventaDescuentaSaldoYCalculaSubtotal() {
        prepararRepositorios(TipoFactura.VENTA);
        when(facturaKardexRepository.save(any(FacturaKardex.class))).thenAnswer(inv -> inv.getArgument(0));

        FacturaKardex guardado = service.guardar(detalle(5, "1500"));

        assertEquals(15, articulo.getArticuloSaldo());
        assertEquals(new BigDecimal("7500.00"), guardado.getFkardexsubtotal());
        verify(articuloRepository).save(articulo);
    }

    @Test
    void compraAumentaSaldo() {
        prepararRepositorios(TipoFactura.COMPRA);
        when(facturaKardexRepository.save(any(FacturaKardex.class))).thenAnswer(inv -> inv.getArgument(0));

        service.guardar(detalle(5, "1000"));

        assertEquals(25, articulo.getArticuloSaldo());
    }

    @Test
    void ventaConSaldoInsuficienteFalla() {
        prepararRepositorios(TipoFactura.VENTA);

        assertThrows(ReglaNegocioException.class, () -> service.guardar(detalle(21, "1500")));
        assertEquals(20, articulo.getArticuloSaldo());
        verify(facturaKardexRepository, never()).save(any(FacturaKardex.class));
    }

    @Test
    void ventaPorDebajoDelCostoFalla() {
        prepararRepositorios(TipoFactura.VENTA);

        assertThrows(ReglaNegocioException.class, () -> service.guardar(detalle(1, "999.99")));
        verify(facturaKardexRepository, never()).save(any(FacturaKardex.class));
    }

    @Test
    void cantidadCeroFalla() {
        assertThrows(ReglaNegocioException.class, () -> service.guardar(detalle(0, "1500")));
    }

    @Test
    void facturaInexistenteFalla() {
        when(facturaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () -> service.guardar(detalle(1, "1500")));
    }
}
