package com.example.seven.AmbiGPSBeta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recicladores")
public class Reciclador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reciclador")
    private Long idReciclador;

    @NotBlank
    private String nombre;

    @NotBlank
    private String telefono;

    @NotBlank
    private String departamento;

    @NotBlank
    private String municipio;

    @NotBlank
    private String estado;

    @OneToMany(mappedBy = "reciclador")
    private List<SolicitudRecoleccion> solicitudes = new ArrayList<>();

    public Reciclador() {
    }

    public Long getIdReciclador() {
        return idReciclador;
    }

    public void setIdReciclador(Long idReciclador) {
        this.idReciclador = idReciclador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<SolicitudRecoleccion> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudRecoleccion> solicitudes) {
        this.solicitudes = solicitudes;
    }
}