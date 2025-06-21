package com.elecciones.backend.voto.exceptions;

public class CircuitoCerradoException extends RuntimeException {
    public CircuitoCerradoException(String message) {
        super(message);
    }
}