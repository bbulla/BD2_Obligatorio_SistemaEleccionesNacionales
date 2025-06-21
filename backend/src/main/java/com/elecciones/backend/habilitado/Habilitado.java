package com.elecciones.backend.habilitado;

import java.time.LocalDate;

public class Habilitado {
    private String cc;
    private String ci;
    private String nombres;
    private String apellidos;
    private LocalDate fNacimiento;
    private LocalDate fechaVoto;
    private Integer posicion;
    private int idCircuito;
    private int idPartido;

    public Habilitado() {}

    public Habilitado(String cc, String ci, String nombres, String apellidos, LocalDate fNacimiento,
                      LocalDate fechaVoto, Integer posicion, int idCircuito, int idPartido) {
        this.cc = cc;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fNacimiento = fNacimiento;
        this.fechaVoto = fechaVoto;
        this.posicion = posicion;
        this.idCircuito = idCircuito;
        this.idPartido = idPartido;
    }

    public String getCc() { return cc; }
    public void setCc(String cc) { this.cc = cc; }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public LocalDate getFNacimiento() { return fNacimiento; }
    public void setFNacimiento(LocalDate fNacimiento) { this.fNacimiento = fNacimiento; }

    public LocalDate getFechaVoto() { return fechaVoto; }
    public void setFechaVoto(LocalDate fechaVoto) { this.fechaVoto = fechaVoto; }

    public Integer getPosicion() { return posicion; }
    public void setPosicion(Integer posicion) { this.posicion = posicion; }

    public int getIdCircuito() { return idCircuito; }
    public void setIdCircuito(int idCircuito) { this.idCircuito = idCircuito; }

    public int getIdPartido() { return idPartido; }
    public void setIdPartido(int idPartido) { this.idPartido = idPartido; }
}