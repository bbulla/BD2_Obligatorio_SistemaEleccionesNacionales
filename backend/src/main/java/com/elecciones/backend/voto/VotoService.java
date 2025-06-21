package com.elecciones.backend.voto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    public List<Voto> getAllVotos() {
        return votoRepository.findAll();
    }
}