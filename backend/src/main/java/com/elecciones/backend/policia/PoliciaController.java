package com.elecciones.backend.policia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policias")
public class PoliciaController {

    @Autowired
    private PoliciaService policiaService;

    @GetMapping
    public List<Policia> getAll() {
        return policiaService.getAllPolicias();
    }
}