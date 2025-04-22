package com.pruebatecnica.kardexone.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.kardexone.Model.Cartera;
import com.pruebatecnica.kardexone.Repository.CarteraRepository;

@Service
public class CarteraService {

    @Autowired
    private CarteraRepository carteraRepository;

    public List<Cartera> obtenerTodos() {
        return carteraRepository.findAll();
    }

    public Optional<Cartera> obtenerPorId(Long id) {
        return carteraRepository.findById(id);
    }

    public Cartera guardar(Cartera cartera) {
        return carteraRepository.save(cartera);
    }

    public void eliminar(Long id) {
        carteraRepository.deleteById(id);
    }
}
