package com.elecciones.backend.eleccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleccionService {

    @Autowired
    private EleccionRepository eleccionRepository;

    public List<Eleccion> getAllElecciones() {
        return eleccionRepository.findAll();
    }
}
