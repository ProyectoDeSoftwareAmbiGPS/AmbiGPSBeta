package com.example.seven.AmbiGPSBeta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solicitudes_recoleccion")
public class SolicitudRecoleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reciclador", nullable = true)
    private Usuario reciclador;

    @NotNull(message = "El tipo de material es obligatorio")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_material", nullable = false)
    private TipoMaterial tipoMaterial;

    @Column(name = "foto_reciclaje", length = 255)
    private String fotoReciclaje;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;

    @Column(name = "fecha_solicitud", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_finalizacion", nullable = true)
    private LocalDateTime fechaFinalizacion;

    @OneToMany(
        mappedBy = "solicitud",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Reciclaje> reciclajes = new ArrayList<>();

    @OneToOne(
        mappedBy = "solicitud",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private QR qr;

    @OneToMany(
        mappedBy = "solicitud",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Notificacion> notificaciones = new ArrayList<>();

    public SolicitudRecoleccion() {
        this.fechaSolicitud = LocalDateTime.now();
        this.estado = EstadoSolicitud.PENDIENTE;
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getReciclador() {
        return reciclador;
    }

    public void setReciclador(Usuario reciclador) {
        this.reciclador = reciclador;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getFotoReciclaje() {
        return fotoReciclaje;
    }

    public void setFotoReciclaje(String fotoReciclaje) {
        this.fotoReciclaje = fotoReciclaje;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDateTime getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDateTime fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public List<Reciclaje> getReciclajes() {
        return reciclajes;
    }

    public void setReciclajes(List<Reciclaje> reciclajes) {
        this.reciclajes = reciclajes;
    }

    public QR getQr() {
        return qr;
    }

    public void setQr(QR qr) {
        this.qr = qr;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }
}