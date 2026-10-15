package com.example.seven.AmbiGPSBeta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipos_material")
public class TipoMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_material")
    private Integer idTipoMaterial;

    @NotBlank(message = "El nombre del tipo de material es obligatorio")
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @OneToMany(
        mappedBy = "tipoMaterial",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<SolicitudRecoleccion> solicitudes = new ArrayList<>();

    public TipoMaterial() {
    }

    public TipoMaterial(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoMaterial() {
        return idTipoMaterial;
    }

    public void setIdTipoMaterial(Integer idTipoMaterial) {
        this.idTipoMaterial = idTipoMaterial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<SolicitudRecoleccion> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudRecoleccion> solicitudes) {
        this.solicitudes = solicitudes;
    }
}