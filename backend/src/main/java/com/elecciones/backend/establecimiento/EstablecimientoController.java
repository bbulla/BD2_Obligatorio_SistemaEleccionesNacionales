package com.elecciones.backend.establecimiento;

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