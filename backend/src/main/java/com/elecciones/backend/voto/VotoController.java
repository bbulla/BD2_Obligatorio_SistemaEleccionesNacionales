package com.elecciones.backend.voto;

import com.elecciones.backend.voto.reportes.GanadorEleccionDTO;
import com.elecciones.backend.voto.reportes.GanadorPorDepartamentoDTO;
import com.elecciones.backend.voto.reportes.VotosPorListaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<String> registrarVoto(@RequestBody VotoRequest request) {
        votoService.registrarVoto(request);
        return ResponseEntity.ok("Voto registrado correctamente.");
    }

    @GetMapping("/reportes/eleccion/{id}/ganador")
    public GanadorEleccionDTO ganadorPorEleccion(@PathVariable int id) {
        return votoService.obtenerGanadorPorEleccion(id);
    }

    @GetMapping("/reportes/eleccion/{id}/ganadores-por-departamento")
    public ResponseEntity<List<GanadorPorDepartamentoDTO>> getGanadoresPorDepartamento(@PathVariable int id) {
        return ResponseEntity.ok(votoService.obtenerGanadoresPorDepartamento(id));
    }


}
