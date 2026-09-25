package com.pruebatecnica.kardexone.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.kardexone.Exception.RecursoNoEncontradoException;
import com.pruebatecnica.kardexone.Model.Nit;
import com.pruebatecnica.kardexone.Repository.NitRepository;

@Service
@Transactional(readOnly = true)
public class NitService {

    private final NitRepository nitRepository;

    public NitService(NitRepository nitRepository) {
        this.nitRepository = nitRepository;
    }

    public List<Nit> obtenerTodos() {
        return nitRepository.findAll();
    }

    public Nit obtenerPorId(Long id) {
        return nitRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El cliente (NIT)", id));
    }

    @Transactional
    public Nit guardar(Nit nit) {
        nit.setNitId(null);
        return nitRepository.save(nit);
    }

    @Transactional
    public Nit actualizar(Long id, Nit datos) {
        Nit nit = obtenerPorId(id);
        nit.setNitNombre(datos.getNitNombre());
        nit.setNitDocumento(datos.getNitDocumento());
        nit.setNitCupo(datos.getNitCupo());
        nit.setNitPlazo(datos.getNitPlazo());
        return nitRepository.save(nit);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!nitRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("El cliente (NIT)", id);
        }
        nitRepository.deleteById(id);
    }
}
