package com.elecciones.backend.model;

public class Establecimiento {
    private int id;
    private String nombre;
    private String tipo;
    private int idLocalidad;

    public Establecimiento() {}

    public Establecimiento(int id, String nombre, String tipo, int idLocalidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.idLocalidad = idLocalidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }
}