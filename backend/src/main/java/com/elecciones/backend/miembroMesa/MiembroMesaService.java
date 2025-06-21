package com.elecciones.backend.miembroMesa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiembroMesaService {

    @Autowired
    private MiembroMesaRepository miembroMesaRepository;

    public List<MiembroMesa> getAllMiembrosMesa() {
        return miembroMesaRepository.findAll();
    }
}
