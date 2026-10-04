package com.example.seven.AmbiGPSBeta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "reciclajes")
public class Reciclaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reciclaje")
    private Long idReciclaje;

    @NotBlank
    private String tipo;

    @Positive
    private Double peso;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudRecoleccion solicitud;

    public Reciclaje() {
    }

    public Long getIdReciclaje() {
        return idReciclaje;
    }

    public void setIdReciclaje(Long idReciclaje) {
        this.idReciclaje = idReciclaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public SolicitudRecoleccion getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudRecoleccion solicitud) {
        this.solicitud = solicitud;
    }
}