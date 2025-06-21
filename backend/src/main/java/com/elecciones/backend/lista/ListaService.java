package com.elecciones.backend.lista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;

    public List<Lista> getAllListas() {
        return listaRepository.findAll();
    }
}
