package com.example.seven.AmbiGPSBeta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "qr")
public class QR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_qr")
    private Long idQr;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDate fechaGeneracion;

    @OneToOne
    @JoinColumn(
        name = "id_solicitud",
        nullable = false,
        unique = true
    )
    private SolicitudRecoleccion solicitud;

    public QR() {
    }

    public Long getIdQr() {
        return idQr;
    }

    public void setIdQr(Long idQr) {
        this.idQr = idQr;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public SolicitudRecoleccion getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudRecoleccion solicitud) {
        this.solicitud = solicitud;
    }
}