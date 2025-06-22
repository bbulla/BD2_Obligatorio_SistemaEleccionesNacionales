package com.elecciones.backend.circuito;

import com.elecciones.backend.voto.VotoService;
import com.elecciones.backend.voto.reportes.ResultadoDetalleDTO;
import com.elecciones.backend.voto.reportes.VotosPorListaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CircuitoController {

    @Autowired
    private CircuitoService circuitoService;

    @Autowired
    private VotoService votoService;

    @GetMapping("/circuitos")
    public List<Circuito> getAllCircuitos() {
        return circuitoService.getAllCircuitos();
    }

    @PostMapping("/circuitos/{id}/cerrar")
    public ResponseEntity<String> cerrarCircuito(@PathVariable int id) {
        circuitoService.cerrarCircuito(id);
        return ResponseEntity.ok("Circuito cerrado correctamente.");
    }

    @GetMapping("/circuitos/{id}/establecimiento")
    public ResponseEntity<Integer> obtenerEstablecimientoPorCircuito(@PathVariable int id) {
        Integer idEstablecimiento = circuitoService.obtenerEstablecimientoPorCircuito(id);
        if (idEstablecimiento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(idEstablecimiento);
    }

//    @GetMapping("/circuitos/{id}/resultados")
//    public ResponseEntity<List<VotosPorListaDTO>> obtenerResultadosPorCircuito(@PathVariable int id) {
//        List<VotosPorListaDTO> resultados = votoService.obtenerVotosPorListaEnCircuito(id);
//        return ResponseEntity.ok(resultados);
//    }

    @GetMapping("/circuitos/{id}/resultados/detalle")
    public ResponseEntity<List<ResultadoDetalleDTO>> obtenerResultadosDetallados(@PathVariable int id) {
        return ResponseEntity.ok(votoService.obtenerResultadosDetalladosPorCircuito(id));
    }

}