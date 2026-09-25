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

import com.pruebatecnica.kardexone.Model.Articulo;
import com.pruebatecnica.kardexone.Service.ArticuloService;

@RestController
@RequestMapping("/api/articulo")
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    public List<Articulo> obtenerTodos() {
        return articuloService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Articulo obtenerPorId(@PathVariable Long id) {
        return articuloService.obtenerPorId(id);
    }

    @GetMapping("/codigo/{codigo}")
    public Articulo obtenerPorCodigo(@PathVariable String codigo) {
        return articuloService.obtenerPorCodigo(codigo);
    }

    @PostMapping
    public ResponseEntity<Articulo> guardar(@Valid @RequestBody Articulo articulo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articuloService.guardar(articulo));
    }

    @PutMapping("/{id}")
    public Articulo actualizar(@PathVariable Long id, @Valid @RequestBody Articulo articulo) {
        return articuloService.actualizar(id, articulo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        articuloService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
