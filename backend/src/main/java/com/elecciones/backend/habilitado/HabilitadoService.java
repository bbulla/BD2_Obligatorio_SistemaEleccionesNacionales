package com.elecciones.backend.habilitado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilitadoService {

    @Autowired
    private HabilitadoRepository habilitadoRepository;

    public List<Habilitado> getAllHabilitados() {
        return habilitadoRepository.findAll();
    }

    public Habilitado getByCc(String cc) {
        return habilitadoRepository.findByCc(cc);
    }
}
