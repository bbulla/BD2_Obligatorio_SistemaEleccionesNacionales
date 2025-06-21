package com.elecciones.backend.votoPapeleta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voto-papeleta")
public class VotoPapeletaController {

    @Autowired
    private VotoPapeletaService votoPapeletaService;

    @GetMapping
    public List<VotoPapeleta> getAll() {
        return votoPapeletaService.getAllVotoPapeletas();
    }
}
