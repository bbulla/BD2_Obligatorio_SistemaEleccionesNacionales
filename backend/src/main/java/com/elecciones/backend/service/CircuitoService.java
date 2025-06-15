package com.elecciones.backend.service;

import com.elecciones.backend.model.Circuito;
import com.elecciones.backend.repository.CircuitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircuitoService {

    @Autowired
    private CircuitoRepository circuitoRepository;

    public List<Circuito> getAllCircuitos() {
        return circuitoRepository.findAll();
    }
}