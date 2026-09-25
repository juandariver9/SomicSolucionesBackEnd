package com.pruebatecnica.kardexone.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.kardexone.Exception.RecursoNoEncontradoException;
import com.pruebatecnica.kardexone.Model.Articulo;
import com.pruebatecnica.kardexone.Repository.ArticuloRepository;

@Service
@Transactional(readOnly = true)
public class ArticuloService {

    private final ArticuloRepository articuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public List<Articulo> obtenerTodos() {
        return articuloRepository.findAll();
    }

    public Articulo obtenerPorId(Long id) {
        return articuloRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El artículo", id));
    }

    public Articulo obtenerPorCodigo(String codigo) {
        return articuloRepository.findByArticuloCodigo(codigo)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un artículo con código " + codigo + "."));
    }

    @Transactional
    public Articulo guardar(Articulo articulo) {
        articulo.setArticuloId(null);
        return articuloRepository.save(articulo);
    }

    @Transactional
    public Articulo actualizar(Long id, Articulo datos) {
        Articulo articulo = obtenerPorId(id);
        articulo.setArticuloCodigo(datos.getArticuloCodigo());
        articulo.setArticuloNombre(datos.getArticuloNombre());
        articulo.setArticuloLaboratorio(datos.getArticuloLaboratorio());
        articulo.setArticuloSaldo(datos.getArticuloSaldo());
        articulo.setArticuloCosto(datos.getArticuloCosto());
        articulo.setArticuloPrecioVenta(datos.getArticuloPrecioVenta());
        return articuloRepository.save(articulo);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!articuloRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("El artículo", id);
        }
        articuloRepository.deleteById(id);
    }
}
