package com.example.seven.AmbiGPSBeta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Integer idDepartamento;

    @NotBlank(message = "El nombre del departamento es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany(
        mappedBy = "departamento",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Municipio> municipios = new ArrayList<>();

    public Departamento() {
    }

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
}
