package com.elecciones.backend.policia;

public class Policia {
    private int nroPolicia;
    private String cc;
    private int idComisaria;
    private int idEstablecimiento;

    public Policia() {}

    public Policia(int nroPolicia, String cc, int idComisaria, int idEstablecimiento) {
        this.nroPolicia = nroPolicia;
        this.cc = cc;
        this.idComisaria = idComisaria;
        this.idEstablecimiento = idEstablecimiento;
    }

    public int getNroPolicia() {
        return nroPolicia;
    }

    public void setNroPolicia(int nroPolicia) {
        this.nroPolicia = nroPolicia;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public int getIdComisaria() {
        return idComisaria;
    }

    public void setIdComisaria(int idComisaria) {
        this.idComisaria = idComisaria;
    }

    public int getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(int idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }
}