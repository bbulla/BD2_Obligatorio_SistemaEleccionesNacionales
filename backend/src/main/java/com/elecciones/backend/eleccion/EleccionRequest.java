package com.elecciones.backend.eleccion;

import java.time.LocalDate;

public class EleccionRequest {

    private String descripcion;
    private String tipo;
    private LocalDate fecha;

    public EleccionRequest() {
    }

    public EleccionRequest(String descripcion, String tipo, LocalDate fecha) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
