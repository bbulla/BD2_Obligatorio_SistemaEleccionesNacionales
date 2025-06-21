package com.elecciones.backend.miembroMesa;

public class MiembroMesa {
    private String cc;
    private String organismo;
    private String rol;
    private int idCircuito;

    public MiembroMesa() {}

    public MiembroMesa(String cc, String organismo, String rol, int idCircuito) {
        this.cc = cc;
        this.organismo = organismo;
        this.rol = rol;
        this.idCircuito = idCircuito;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getOrganismo() {
        return organismo;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getIdCircuito() {
        return idCircuito;
    }

    public void setIdCircuito(int idCircuito) {
        this.idCircuito = idCircuito;
    }
}