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

import com.pruebatecnica.kardexone.Model.Cartera;
import com.pruebatecnica.kardexone.Service.CarteraService;

@RestController
@RequestMapping("/api/cartera")
public class CarteraController {

    private final CarteraService carteraService;

    public CarteraController(CarteraService carteraService) {
        this.carteraService = carteraService;
    }

    @GetMapping
    public List<Cartera> obtenerTodos() {
        return carteraService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Cartera obtenerPorId(@PathVariable Long id) {
        return carteraService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Cartera> guardar(@RequestBody Cartera cartera) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carteraService.guardar(cartera));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        carteraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
