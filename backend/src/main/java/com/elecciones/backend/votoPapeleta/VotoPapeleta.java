package com.elecciones.backend.votoPapeleta;

public class VotoPapeleta {
    private int idVoto;
    private int idPapeleta;

    public VotoPapeleta() {}

    public VotoPapeleta(int idVoto, int idPapeleta) {
        this.idVoto = idVoto;
        this.idPapeleta = idPapeleta;
    }

    public int getIdVoto() {
        return idVoto;
    }

    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }

    public int getIdPapeleta() {
        return idPapeleta;
    }

    public void setIdPapeleta(int idPapeleta) {
        this.idPapeleta = idPapeleta;
    }
}
