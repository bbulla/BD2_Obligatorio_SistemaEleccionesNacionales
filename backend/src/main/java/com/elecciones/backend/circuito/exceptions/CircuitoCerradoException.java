package com.elecciones.backend.circuito.exceptions;

public class CircuitoCerradoException extends RuntimeException {
    public CircuitoCerradoException(String msg) {
        super(msg);
    }
}
