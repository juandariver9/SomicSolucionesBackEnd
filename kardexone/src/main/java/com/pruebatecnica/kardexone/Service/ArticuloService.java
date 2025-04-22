package com.pruebatecnica.kardexone.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.kardexone.Model.Articulo;
import com.pruebatecnica.kardexone.Repository.ArticuloRepository;

@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    public List<Articulo> obtenerTodos() {
        return articuloRepository.findAll();
    }

    public Optional<Articulo> obtenerPorId(Long id) {
        return articuloRepository.findById(id);
    }
    
    public Optional<Articulo> obtenerPorCodigo(String codigo) {
        return articuloRepository.findByArticuloCodigo(codigo);
    }
    
    public Articulo guardar(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    public void eliminar(Long id) {
        articuloRepository.deleteById(id);
    }
}
