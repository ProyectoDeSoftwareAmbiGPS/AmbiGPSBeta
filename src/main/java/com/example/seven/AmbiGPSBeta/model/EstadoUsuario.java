package com.example.seven.AmbiGPSBeta.model;

public enum EstadoUsuario {
    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    SUSPENDIDO("Suspendido");

    private final String valor;

    EstadoUsuario(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
