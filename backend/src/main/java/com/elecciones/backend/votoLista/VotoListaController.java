package com.elecciones.backend.votoLista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voto-lista")
public class VotoListaController {

    @Autowired
    private VotoListaService votoListaService;

    @GetMapping
    public List<VotoLista> getAll() {
        return votoListaService.getAllVotoListas();
    }
}
