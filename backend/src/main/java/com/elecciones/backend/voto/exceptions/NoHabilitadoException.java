package com.elecciones.backend.voto.exceptions;

public class NoHabilitadoException extends RuntimeException {
    public NoHabilitadoException(String message) {
        super(message);
    }
}