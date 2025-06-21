package com.elecciones.backend.eleccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elecciones")
public class EleccionController {

    @Autowired
    private EleccionService eleccionService;

    @GetMapping
    public List<Eleccion> getAll() {
        return eleccionService.getAllElecciones();
    }
}
