package com.elecciones.backend.circuito.exceptions;

public class CircuitoNoEncontradoException extends RuntimeException {
    public CircuitoNoEncontradoException(String message) {
        super(message);
    }
}