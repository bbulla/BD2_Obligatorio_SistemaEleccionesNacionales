package com.elecciones.backend.votoLista;

public class VotoLista {
    private int idVoto;
    private int idLista;

    public VotoLista() {}

    public VotoLista(int idVoto, int idLista) {
        this.idVoto = idVoto;
        this.idLista = idLista;
    }

    public int getIdVoto() {
        return idVoto;
    }

    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }
}
