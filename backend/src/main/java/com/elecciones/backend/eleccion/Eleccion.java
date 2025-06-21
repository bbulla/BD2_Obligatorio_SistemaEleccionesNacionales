package com.elecciones.backend.eleccion;

import java.time.LocalDate;

public class Eleccion {
    private int id;
    private String tipo;
    private LocalDate fecha;
    private String descripcion;

    public Eleccion() {}

    public Eleccion(int id, String tipo, LocalDate fecha, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
