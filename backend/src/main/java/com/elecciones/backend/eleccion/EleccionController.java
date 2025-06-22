package com.elecciones.backend.eleccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<String> crearEleccion(@RequestBody EleccionRequest request) {
        eleccionService.saveEleccion(request);
        return ResponseEntity.ok("Elección registrada correctamente.");
    }

}
