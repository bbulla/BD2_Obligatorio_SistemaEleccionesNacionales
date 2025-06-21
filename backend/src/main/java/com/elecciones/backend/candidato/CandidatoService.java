package com.elecciones.backend.candidato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public List<Candidato> getAllCandidatos() {
        return candidatoRepository.findAll();
    }
}
