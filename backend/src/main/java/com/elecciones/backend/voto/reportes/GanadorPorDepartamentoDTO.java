package com.elecciones.backend.voto.reportes;

public class GanadorPorDepartamentoDTO {
    private String departamento;
    private String lista;
    private String partido;
    private int votos;

    public GanadorPorDepartamentoDTO(String departamento, String lista, String partido, int votos) {
        this.departamento = departamento;
        this.lista = lista;
        this.partido = partido;
        this.votos = votos;
    }

    // Getters y setters
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getLista() { return lista; }
    public void setLista(String lista) { this.lista = lista; }

    public String getPartido() { return partido; }
    public void setPartido(String partido) { this.partido = partido; }

    public int getVotos() { return votos; }
    public void setVotos(int votos) { this.votos = votos; }
}