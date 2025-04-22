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

import com.pruebatecnica.kardexone.Model.Cartera;
import com.pruebatecnica.kardexone.Service.CarteraService;

@RestController
@RequestMapping("/api/cartera")
public class CarteraController {

    @Autowired
    private CarteraService carteraService;

    @GetMapping
    public List<Cartera> obtenerTodos() {
        return carteraService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Cartera> obtenerPorId(@PathVariable Long id) {
        return carteraService.obtenerPorId(id);
    }

    @PostMapping
    public Cartera guardar(@RequestBody Cartera cartera) {
        return carteraService.guardar(cartera);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        carteraService.eliminar(id);
    }
}
