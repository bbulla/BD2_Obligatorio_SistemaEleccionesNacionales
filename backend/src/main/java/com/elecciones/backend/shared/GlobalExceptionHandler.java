package com.elecciones.backend.shared;

import com.elecciones.backend.circuito.exceptions.CircuitoNoEncontradoException;
import com.elecciones.backend.voto.exceptions.CircuitoCerradoException;
import com.elecciones.backend.voto.exceptions.NoHabilitadoException;
import com.elecciones.backend.voto.exceptions.YaVotoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(YaVotoException.class)
    public ResponseEntity<String> handleYaVoto(YaVotoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CircuitoCerradoException.class)
    public ResponseEntity<String> handleCircuitoCerrado(CircuitoCerradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CircuitoNoEncontradoException.class)
    public ResponseEntity<String> handleCircuitoNoEncontrado(CircuitoNoEncontradoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NoHabilitadoException.class)
    public ResponseEntity<String> handleNoHabilitado(NoHabilitadoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


}
