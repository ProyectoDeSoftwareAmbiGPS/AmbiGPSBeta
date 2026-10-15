package com.example.seven.AmbiGPSBeta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "qr")
public class QR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_qr")
    private Long idQr;

    @NotNull(message = "La solicitud es obligatoria")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud", nullable = false, unique = true)
    private SolicitudRecoleccion solicitud;

    @NotBlank(message = "El código QR es obligatorio")
    @Column(nullable = false, unique = true, length = 255)
    private String codigo;

    @Column(name = "fecha_generacion", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaGeneracion;

    public QR() {
        this.fechaGeneracion = LocalDateTime.now();
    }

    public Long getIdQr() {
        return idQr;
    }

    public void setIdQr(Long idQr) {
        this.idQr = idQr;
    }

    public SolicitudRecoleccion getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudRecoleccion solicitud) {
        this.solicitud = solicitud;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
}