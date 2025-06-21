package com.elecciones.backend.circuito;

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