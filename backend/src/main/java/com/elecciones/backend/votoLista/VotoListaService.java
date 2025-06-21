package com.elecciones.backend.votoLista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoListaService {

    @Autowired
    private VotoListaRepository votoListaRepository;

    public List<VotoLista> getAllVotoListas() {
        return votoListaRepository.findAll();
    }
}
