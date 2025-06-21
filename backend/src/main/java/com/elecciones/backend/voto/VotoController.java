package com.elecciones.backend.voto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @GetMapping
    public List<Voto> getAll() {
        return votoService.getAllVotos();
    }
}
