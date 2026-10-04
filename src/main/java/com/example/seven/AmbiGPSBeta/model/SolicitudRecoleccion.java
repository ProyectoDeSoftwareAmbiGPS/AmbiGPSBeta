package com.example.seven.AmbiGPSBeta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solicitudes_recoleccion")
public class SolicitudRecoleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @NotBlank
    @Column(nullable = false)
    private String estado;

    @NotBlank
    @Column(name = "tipo_material", nullable = false)
    private String tipoMaterial;

    @Column(name = "foto_reciclaje")
    private String fotoReciclaje;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_reciclador")
    private Reciclador reciclador;

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

    public SolicitudRecoleccion() {
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getFotoReciclaje() {
        return fotoReciclaje;
    }

    public void setFotoReciclaje(String fotoReciclaje) {
        this.fotoReciclaje = fotoReciclaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Reciclador getReciclador() {
        return reciclador;
    }

    public void setReciclador(Reciclador reciclador) {
        this.reciclador = reciclador;
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
}