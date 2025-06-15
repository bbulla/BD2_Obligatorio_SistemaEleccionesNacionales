package com.elecciones.backend.model;

public class Departamento {
    private int id;
    private String nombre;

    // Constructor vacío
    public Departamento() {}

    // Constructor completo
    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
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
}