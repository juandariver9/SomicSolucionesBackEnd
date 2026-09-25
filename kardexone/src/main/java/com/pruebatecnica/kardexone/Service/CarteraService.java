package com.pruebatecnica.kardexone.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.kardexone.Exception.RecursoNoEncontradoException;
import com.pruebatecnica.kardexone.Model.Cartera;
import com.pruebatecnica.kardexone.Repository.CarteraRepository;

@Service
@Transactional(readOnly = true)
public class CarteraService {

    private final CarteraRepository carteraRepository;

    public CarteraService(CarteraRepository carteraRepository) {
        this.carteraRepository = carteraRepository;
    }

    public List<Cartera> obtenerTodos() {
        return carteraRepository.findAll();
    }

    public Cartera obtenerPorId(Long id) {
        return carteraRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("La cartera", id));
    }

    @Transactional
    public Cartera guardar(Cartera cartera) {
        return carteraRepository.save(cartera);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!carteraRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("La cartera", id);
        }
        carteraRepository.deleteById(id);
    }
}
