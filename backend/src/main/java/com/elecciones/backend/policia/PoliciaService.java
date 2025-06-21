package com.elecciones.backend.policia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliciaService {

    @Autowired
    private PoliciaRepository policiaRepository;

    public List<Policia> getAllPolicias() {
        return policiaRepository.findAll();
    }
}
