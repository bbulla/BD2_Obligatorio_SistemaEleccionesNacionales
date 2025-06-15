package com.elecciones.backend.controller;

import com.elecciones.backend.model.Establecimiento;
import com.elecciones.backend.service.EstablecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/establecimientos")
public class EstablecimientoController {

    @Autowired
    private EstablecimientoService establecimientoService;

    @GetMapping
    public List<Establecimiento> getAll() {
        return establecimientoService.getAllEstablecimientos();
    }
}