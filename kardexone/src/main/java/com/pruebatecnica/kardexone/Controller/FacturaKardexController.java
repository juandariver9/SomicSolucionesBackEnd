package com.pruebatecnica.kardexone.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FacturaKardexService facturaKardexService;
    
    @GetMapping
    public List<FacturaKardex> obtenerTodos() {
        return facturaKardexService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<FacturaKardex> obtenerPorId(@PathVariable Long id) {
        return facturaKardexService.obtenerPorId(id);
    }

    @PostMapping
    public FacturaKardex guardar(@RequestBody FacturaKardex facturaKardex) {
        return facturaKardexService.guardar(facturaKardex);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        facturaKardexService.eliminar(id);
    }
}
