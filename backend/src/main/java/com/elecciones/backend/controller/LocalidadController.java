package com.elecciones.backend.controller;

import com.elecciones.backend.model.Localidad;
import com.elecciones.backend.service.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/localidades")
public class LocalidadController {

    @Autowired
    private LocalidadService localidadService;

    @GetMapping
    public List<Localidad> getAll() {
        return localidadService.getAllLocalidades();
    }
}