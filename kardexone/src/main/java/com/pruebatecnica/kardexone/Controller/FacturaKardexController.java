package com.pruebatecnica.kardexone.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.kardexone.Model.FacturaKardex;
import com.pruebatecnica.kardexone.Service.FacturaKardexService;

@RestController
@RequestMapping("/api/facturakardex")
public class FacturaKardexController {

    private final FacturaKardexService facturaKardexService;

    public FacturaKardexController(FacturaKardexService facturaKardexService) {
        this.facturaKardexService = facturaKardexService;
    }

    @GetMapping
    public List<FacturaKardex> obtenerTodos() {
        return facturaKardexService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public FacturaKardex obtenerPorId(@PathVariable Long id) {
        return facturaKardexService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<FacturaKardex> guardar(@RequestBody FacturaKardex facturaKardex) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaKardexService.guardar(facturaKardex));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facturaKardexService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
