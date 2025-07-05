package com.elecciones.backend.habilitado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{cc}")
    public ResponseEntity<Habilitado> getByCc(@PathVariable String cc) {
        Habilitado habilitado = habilitadoService.getByCc(cc);
        if (habilitado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(habilitado);
    }
    @PostMapping("/login")
    public ResponseEntity<Habilitado> login(@RequestBody LoginRequest loginRequest) {
        Habilitado habilitado = habilitadoService.getByCc(loginRequest.getCc());

        if (habilitado == null || !habilitado.getCi().equals(loginRequest.getCi())) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        return ResponseEntity.ok(habilitado);
    }
}
