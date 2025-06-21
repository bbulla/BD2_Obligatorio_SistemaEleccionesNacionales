package com.elecciones.backend.papeleta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/papeletas")
public class PapeletaController {

    @Autowired
    private PapeletaService papeletaService;

    @GetMapping
    public List<Papeleta> getAll() {
        return papeletaService.getAllPapeletas();
    }
}
