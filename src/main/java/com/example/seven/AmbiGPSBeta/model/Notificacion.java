package com.example.seven.AmbiGPSBeta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Long idNotificacion;

    @NotBlank
    private String mensaje;

    @Column(nullable = false)
    private LocalDate fecha;

    @NotBlank
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudRecoleccion solicitud;

    @ManyToOne
    @JoinColumn(name = "id_reciclador")
    private Reciclador reciclador;

    public Notificacion() {
    }

    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public SolicitudRecoleccion getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudRecoleccion solicitud) {
        this.solicitud = solicitud;
    }

    public Reciclador getReciclador() {
        return reciclador;
    }

    public void setReciclador(Reciclador reciclador) {
        this.reciclador = reciclador;
    }
}