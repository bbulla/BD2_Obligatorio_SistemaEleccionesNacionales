package com.elecciones.backend.controller;

import com.elecciones.backend.model.Departamento;
import com.elecciones.backend.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public List<Departamento> getAll() {
        return departamentoService.getAllDepartamentos();
    }
}