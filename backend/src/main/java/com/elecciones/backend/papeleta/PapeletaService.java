package com.elecciones.backend.papeleta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PapeletaService {

    @Autowired
    private PapeletaRepository papeletaRepository;

    public List<Papeleta> getAllPapeletas() {
        return papeletaRepository.findAll();
    }
}
