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

import com.pruebatecnica.kardexone.Model.Articulo;
import com.pruebatecnica.kardexone.Service.ArticuloService;

@RestController
@RequestMapping("/api/articulo")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping
    public List<Articulo> obtenerTodos() {
        return articuloService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Articulo> obtenerPorId(@PathVariable Long id) {
        return articuloService.obtenerPorId(id);
    }

    @GetMapping("/codigo/{codigo}")
    public Optional<Articulo> obtenerPorCodigo(@PathVariable String codigo) {
        return articuloService.obtenerPorCodigo(codigo);
    }


    @PostMapping
    public Articulo guardar(@RequestBody Articulo articulo) {
        return articuloService.guardar(articulo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        articuloService.eliminar(id);
    }
}
