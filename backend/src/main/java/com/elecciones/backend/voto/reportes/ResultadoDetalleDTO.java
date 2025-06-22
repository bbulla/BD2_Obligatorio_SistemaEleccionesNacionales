package com.elecciones.backend.voto.reportes;

public class ResultadoDetalleDTO {
    private String tipo;
    private int cantidad;
    private double porcentaje;

    public ResultadoDetalleDTO(String tipo, int cantidad, double porcentaje) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.porcentaje = porcentaje;
    }

    // Getters y setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
}