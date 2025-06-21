package com.elecciones.backend.circuito;

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