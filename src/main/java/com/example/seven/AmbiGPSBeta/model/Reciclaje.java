package com.example.seven.AmbiGPSBeta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reciclajes")
public class Reciclaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reciclaje")
    private Long idReciclaje;

    @NotNull(message = "La solicitud es obligatoria")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudRecoleccion solicitud;

    @NotNull(message = "El peso es obligatorio")
    @DecimalMin(value = "0.01", message = "El peso debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pesoKg;

    @Column(length = 255)
    private String descripcion;

    @Column(name = "fecha_registro", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

    public Reciclaje() {
        this.fechaRegistro = LocalDateTime.now();
    }

    public Long getIdReciclaje() {
        return idReciclaje;
    }

    public void setIdReciclaje(Long idReciclaje) {
        this.idReciclaje = idReciclaje;
    }

    public SolicitudRecoleccion getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudRecoleccion solicitud) {
        this.solicitud = solicitud;
    }

    public BigDecimal getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}