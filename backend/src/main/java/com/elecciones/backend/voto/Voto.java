package com.elecciones.backend.voto;

public class Voto {
    private int id;
    private int idCircuito;
    private int idEleccion;
    private boolean esObservado;
    private String tipo;

    public Voto() {}

    public Voto(int id, int idCircuito, int idEleccion, boolean esObservado, String tipo) {
        this.id = id;
        this.idCircuito = idCircuito;
        this.idEleccion = idEleccion;
        this.esObservado = esObservado;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCircuito() {
        return idCircuito;
    }

    public void setIdCircuito(int idCircuito) {
        this.idCircuito = idCircuito;
    }

    public int getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(int idEleccion) {
        this.idEleccion = idEleccion;
    }

    public boolean isEsObservado() {
        return esObservado;
    }

    public void setEsObservado(boolean esObservado) {
        this.esObservado = esObservado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
