package com.example.seven.AmbiGPSBeta.model;

public enum EstadoSolicitud {
    PENDIENTE("Pendiente"),
    ASIGNADO("Asignado"),
    EN_CAMINO("En Camino"),
    COMPLETADO("Completado"),
    CANCELADO("Cancelado");

    private final String valor;

    EstadoSolicitud(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
