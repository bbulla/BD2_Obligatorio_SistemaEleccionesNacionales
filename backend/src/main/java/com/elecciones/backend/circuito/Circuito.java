package com.elecciones.backend.circuito;

public class Circuito {
    private int id;
    private int idEstablecimiento;
    private boolean esAccesible;
    private boolean cerrado;

    public Circuito() {}

    public Circuito(int id, int idEstablecimiento, boolean esAccesible, boolean cerrado) {
        this.id = id;
        this.idEstablecimiento = idEstablecimiento;
        this.esAccesible = esAccesible;
        this.cerrado = cerrado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdEstablecimiento() { return idEstablecimiento; }
    public void setIdEstablecimiento(int idEstablecimiento) { this.idEstablecimiento = idEstablecimiento; }

    public boolean isEsAccesible() { return esAccesible; }
    public void setEsAccesible(boolean esAccesible) { this.esAccesible = esAccesible; }

    public boolean isCerrado() { return cerrado; }
    public void setCerrado(boolean cerrado) { this.cerrado = cerrado; }
}