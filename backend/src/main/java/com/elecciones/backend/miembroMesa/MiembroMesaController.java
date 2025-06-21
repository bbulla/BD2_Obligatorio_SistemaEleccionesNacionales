package com.elecciones.backend.miembroMesa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miembros-mesa")
public class MiembroMesaController {

    @Autowired
    private MiembroMesaService miembroMesaService;

    @GetMapping
    public List<MiembroMesa> getAll() {
        return miembroMesaService.getAllMiembrosMesa();
    }
}
