package com.pruebatecnica.kardexone.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.kardexone.Model.Nit;
import com.pruebatecnica.kardexone.Repository.NitRepository;

@Service
public class NitService {

    @Autowired
    private NitRepository nitRepository;

    public List<Nit> obtenerTodos() {
        return nitRepository.findAll();
    }

    public Optional<Nit> obtenerPorId(Long id) {
        return nitRepository.findById(id);
    }

    public Nit guardar(Nit nit) {
        return nitRepository.save(nit);
    }

    public void eliminar(Long id) {
        nitRepository.deleteById(id);
    }
}
