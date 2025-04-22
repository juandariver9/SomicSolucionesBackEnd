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

import com.pruebatecnica.kardexone.Model.Nit;
import com.pruebatecnica.kardexone.Service.NitService;

@RestController
@RequestMapping("/api/nit")
public class NitController {

    @Autowired
    private NitService nitService;

    @GetMapping
    public List<Nit> obtenerTodos() {
        return nitService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Nit> obtenerPorId(@PathVariable Long id) {
        return nitService.obtenerPorId(id);
    }

    @PostMapping
    public Nit guardar(@RequestBody Nit nit) {
        return nitService.guardar(nit);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        nitService.eliminar(id);
    }
}

