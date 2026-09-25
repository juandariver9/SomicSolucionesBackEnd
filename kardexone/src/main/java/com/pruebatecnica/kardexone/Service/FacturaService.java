package com.pruebatecnica.kardexone.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.kardexone.Exception.RecursoNoEncontradoException;
import com.pruebatecnica.kardexone.Exception.ReglaNegocioException;
import com.pruebatecnica.kardexone.Model.Cartera;
import com.pruebatecnica.kardexone.Model.Factura;
import com.pruebatecnica.kardexone.Model.Nit;
import com.pruebatecnica.kardexone.Model.TipoFactura;
import com.pruebatecnica.kardexone.Repository.CarteraRepository;
import com.pruebatecnica.kardexone.Repository.FacturaRepository;
import com.pruebatecnica.kardexone.Repository.NitRepository;

@Service
@Transactional(readOnly = true)
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final NitRepository nitRepository;
    private final CarteraRepository carteraRepository;

    public FacturaService(FacturaRepository facturaRepository, NitRepository nitRepository,
            CarteraRepository carteraRepository) {
        this.facturaRepository = facturaRepository;
        this.nitRepository = nitRepository;
        this.carteraRepository = carteraRepository;
    }

    public List<Factura> obtenerTodos() {
        return facturaRepository.findAll();
    }

    public Factura obtenerPorId(Long id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("La factura", id));
    }

    /**
     * Registra una factura y su cartera en una sola transacción.
     * La fecha y el vencimiento los calcula el servidor según el plazo del cliente.
     * En las ventas se valida que el cliente no supere su cupo de crédito.
     */
    @Transactional
    public Factura guardar(Factura factura) {
        if (factura.getNit() == null || factura.getNit().getNitId() == null) {
            throw new ReglaNegocioException("La factura debe tener un cliente (NIT).");
        }
        if (factura.getFacturaTipo() == null) {
            throw new ReglaNegocioException("El tipo de factura es obligatorio (VENTA o COMPRA).");
        }
        if (factura.getFacturaTotal() == null || factura.getFacturaTotal().signum() <= 0) {
            throw new ReglaNegocioException("El total de la factura debe ser mayor a cero.");
        }

        Long nitId = factura.getNit().getNitId();
        Nit nit = nitRepository.findById(nitId)
                .orElseThrow(() -> new RecursoNoEncontradoException("El cliente (NIT)", nitId));

        if (factura.getFacturaTipo() == TipoFactura.VENTA) {
            validarCupo(nit, factura.getFacturaTotal());
        }

        LocalDate fechaFactura = LocalDate.now();
        int plazo = nit.getNitPlazo() != null ? nit.getNitPlazo() : 0;

        factura.setFacturaId(null);
        factura.setNit(nit);
        factura.setFacturaFecha(fechaFactura);
        factura.setFacturaFechaVencimiento(fechaFactura.plusDays(plazo));

        Factura facturaGuardada = facturaRepository.save(factura);

        Cartera cartera = new Cartera();
        cartera.setCarteraFecha(fechaFactura);
        cartera.setCarteraValor_pendiente(facturaGuardada.getFacturaTotal());
        cartera.setFactura(facturaGuardada);
        cartera.setNit(nit);
        carteraRepository.save(cartera);

        return facturaGuardada;
    }

    /**
     * Un cupo de 0 (o sin definir) significa que el cliente no tiene límite configurado.
     */
    private void validarCupo(Nit nit, BigDecimal totalNuevaFactura) {
        BigDecimal cupo = nit.getNitCupo();
        if (cupo == null || cupo.signum() <= 0) {
            return;
        }
        BigDecimal pendiente = carteraRepository.sumarPendientePorNitYTipo(nit.getNitId(), TipoFactura.VENTA);
        if (pendiente == null) {
            pendiente = BigDecimal.ZERO;
        }
        BigDecimal disponible = cupo.subtract(pendiente);
        if (totalNuevaFactura.compareTo(disponible) > 0) {
            throw new ReglaNegocioException("La factura supera el cupo disponible del cliente. Cupo: "
                    + cupo + ", pendiente: " + pendiente + ", disponible: " + disponible.max(BigDecimal.ZERO) + ".");
        }
    }

    @Transactional
    public void eliminar(Long id) {
        if (!facturaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("La factura", id);
        }
        facturaRepository.deleteById(id);
    }
}
