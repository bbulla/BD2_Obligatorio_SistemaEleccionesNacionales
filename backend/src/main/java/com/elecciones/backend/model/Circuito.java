package com.elecciones.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Circuito {
    private int id;
    private int idEstablecimiento;
    private boolean esAccesible;
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;

    public Circuito() {}

    public Circuito(int id, int idEstablecimiento, boolean esAccesible, LocalDate fecha, LocalTime hora, String descripcion) {
        this.id = id;
        this.idEstablecimiento = idEstablecimiento;
        this.esAccesible = esAccesible;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdEstablecimiento() { return idEstablecimiento; }
    public void setIdEstablecimiento(int idEstablecimiento) { this.idEstablecimiento = idEstablecimiento; }

    public boolean isEsAccesible() { return esAccesible; }
    public void setEsAccesible(boolean esAccesible) { this.esAccesible = esAccesible; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}