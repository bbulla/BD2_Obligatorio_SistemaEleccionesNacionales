package com.elecciones.backend.controller;

import com.elecciones.backend.model.Circuito;
import com.elecciones.backend.service.CircuitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/circuitos")
public class CircuitoController {

    @Autowired
    private CircuitoService circuitoService;

    @GetMapping
    public List<Circuito> getAll() {
        return circuitoService.getAllCircuitos();
    }
}