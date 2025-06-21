package com.elecciones.backend.circuito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CircuitoController {

    @Autowired
    private CircuitoService circuitoService;

    @PostMapping("/establecimientos/{idEstablecimiento}/circuitos/{idCircuito}/cerrar")
    public ResponseEntity<String> cerrarCircuito(
            @PathVariable int idEstablecimiento,
            @PathVariable int idCircuito
    ) {
        circuitoService.cerrarCircuito(idCircuito, idEstablecimiento);
        return ResponseEntity.ok("Circuito cerrado correctamente.");
    }
}