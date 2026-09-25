package com.pruebatecnica.kardexone.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pruebatecnica.kardexone.Exception.RecursoNoEncontradoException;
import com.pruebatecnica.kardexone.Exception.ReglaNegocioException;
import com.pruebatecnica.kardexone.Model.Cartera;
import com.pruebatecnica.kardexone.Model.Factura;
import com.pruebatecnica.kardexone.Model.Nit;
import com.pruebatecnica.kardexone.Model.TipoFactura;
import com.pruebatecnica.kardexone.Repository.CarteraRepository;
import com.pruebatecnica.kardexone.Repository.FacturaRepository;
import com.pruebatecnica.kardexone.Repository.NitRepository;

@ExtendWith(MockitoExtension.class)
class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private NitRepository nitRepository;

    @Mock
    private CarteraRepository carteraRepository;

    @InjectMocks
    private FacturaService service;

    private Nit cliente;

    @BeforeEach
    void setUp() {
        cliente = new Nit();
        cliente.setNitId(1L);
        cliente.setNitCupo(new BigDecimal("100000"));
        cliente.setNitPlazo(30);
    }

    private Factura factura(TipoFactura tipo, String total) {
        Nit ref = new Nit();
        ref.setNitId(1L);
        Factura factura = new Factura();
        factura.setNit(ref);
        factura.setFacturaTipo(tipo);
        factura.setFacturaTotal(new BigDecimal(total));
        return factura;
    }

    @Test
    void ventaDentroDelCupoCalculaVencimientoYCreaCartera() {
        when(nitRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(carteraRepository.sumarPendientePorNitYTipo(1L, TipoFactura.VENTA)).thenReturn(new BigDecimal("40000"));
        when(facturaRepository.save(any(Factura.class))).thenAnswer(inv -> inv.getArgument(0));

        Factura guardada = service.guardar(factura(TipoFactura.VENTA, "60000"));

        assertEquals(LocalDate.now(), guardada.getFacturaFecha());
        assertEquals(LocalDate.now().plusDays(30), guardada.getFacturaFechaVencimiento());
        verify(carteraRepository).save(any(Cartera.class));
    }

    @Test
    void ventaQueSuperaElCupoFalla() {
        when(nitRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(carteraRepository.sumarPendientePorNitYTipo(1L, TipoFactura.VENTA)).thenReturn(new BigDecimal("40000"));

        assertThrows(ReglaNegocioException.class, () -> service.guardar(factura(TipoFactura.VENTA, "60000.01")));
        verify(facturaRepository, never()).save(any(Factura.class));
    }

    @Test
    void clienteSinCupoConfiguradoNoSeValida() {
        cliente.setNitCupo(BigDecimal.ZERO);
        when(nitRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(facturaRepository.save(any(Factura.class))).thenAnswer(inv -> inv.getArgument(0));

        service.guardar(factura(TipoFactura.VENTA, "999999"));

        verify(carteraRepository, never()).sumarPendientePorNitYTipo(any(), any());
    }

    @Test
    void compraNoValidaCupo() {
        when(nitRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(facturaRepository.save(any(Factura.class))).thenAnswer(inv -> inv.getArgument(0));

        service.guardar(factura(TipoFactura.COMPRA, "500000"));

        verify(carteraRepository, never()).sumarPendientePorNitYTipo(any(), any());
    }

    @Test
    void clienteInexistenteFalla() {
        when(nitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () -> service.guardar(factura(TipoFactura.VENTA, "1000")));
    }

    @Test
    void totalCeroFalla() {
        assertThrows(ReglaNegocioException.class, () -> service.guardar(factura(TipoFactura.VENTA, "0")));
    }
}
