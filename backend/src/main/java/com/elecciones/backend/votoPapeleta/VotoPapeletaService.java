package com.elecciones.backend.votoPapeleta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoPapeletaService {

    @Autowired
    private VotoPapeletaRepository votoPapeletaRepository;

    public List<VotoPapeleta> getAllVotoPapeletas() {
        return votoPapeletaRepository.findAll();
    }
}
