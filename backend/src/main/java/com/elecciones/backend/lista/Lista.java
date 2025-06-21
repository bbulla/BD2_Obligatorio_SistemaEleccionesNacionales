package com.elecciones.backend.lista;

public class Lista {
    private int id;
    private String lema;
    private int nroLista;
    private int idEleccion;

    public Lista() {}

    public Lista(int id, String lema, int nroLista, int idEleccion) {
        this.id = id;
        this.lema = lema;
        this.nroLista = nroLista;
        this.idEleccion = idEleccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLema() {
        return lema;
    }

    public void setLema(String lema) {
        this.lema = lema;
    }

    public int getNroLista() {
        return nroLista;
    }

    public void setNroLista(int nroLista) {
        this.nroLista = nroLista;
    }

    public int getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(int idEleccion) {
        this.idEleccion = idEleccion;
    }
}
