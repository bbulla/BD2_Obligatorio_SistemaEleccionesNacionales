package com.elecciones.backend.voto.reportes;

public class VotosPorListaDTO {
    public int idLista;
    public String nombreLista;
    public String nombrePartido;
    public int totalVotos;

    public VotosPorListaDTO(int idLista, String nombreLista, String nombrePartido, int totalVotos) {
        this.idLista = idLista;
        this.nombreLista = nombreLista;
        this.nombrePartido = nombrePartido;
        this.totalVotos = totalVotos;
    }
}
