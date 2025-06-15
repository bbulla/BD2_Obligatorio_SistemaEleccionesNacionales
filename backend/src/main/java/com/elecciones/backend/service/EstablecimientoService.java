package com.elecciones.backend.service;

import com.elecciones.backend.model.Establecimiento;
import com.elecciones.backend.repository.EstablecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstablecimientoService {

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    public List<Establecimiento> getAllEstablecimientos() {
        return establecimientoRepository.findAll();
    }
}