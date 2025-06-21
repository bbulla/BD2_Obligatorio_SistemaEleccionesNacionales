package com.elecciones.backend.habilitado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habilitados")
public class HabilitadoController {

    @Autowired
    private HabilitadoService habilitadoService;

    @GetMapping
    public List<Habilitado> getAll() {
        return habilitadoService.getAllHabilitados();
    }
}
