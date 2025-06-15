package com.elecciones.backend.service;

import com.elecciones.backend.model.Localidad;
import com.elecciones.backend.repository.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadService {

    @Autowired
    private LocalidadRepository localidadRepository;

    public List<Localidad> getAllLocalidades() {
        return localidadRepository.findAll();
    }
}