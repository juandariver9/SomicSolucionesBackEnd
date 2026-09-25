package com.pruebatecnica.kardexone.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.pruebatecnica.kardexone.Model.Nit;
import com.pruebatecnica.kardexone.Service.NitService;

@RestController
@RequestMapping("/api/nit")
public class NitController {

    private final NitService nitService;

    public NitController(NitService nitService) {
        this.nitService = nitService;
    }

    @GetMapping
    public List<Nit> obtenerTodos() {
        return nitService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Nit obtenerPorId(@PathVariable Long id) {
        return nitService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Nit> guardar(@Valid @RequestBody Nit nit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nitService.guardar(nit));
    }

    @PutMapping("/{id}")
    public Nit actualizar(@PathVariable Long id, @Valid @RequestBody Nit nit) {
        return nitService.actualizar(id, nit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        nitService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
