package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.Notificacion;
import com.example.seven.AmbiGPSBeta.repository.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public Notificacion crearNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> listarNotificaciones() {
        return notificacionRepository.findAll();
    }

    public Notificacion buscarPorId(Long id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Notificación no encontrada con id: " + id));
    }

    public List<Notificacion> buscarPorUsuario(Long idUsuario) {
        return notificacionRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<Notificacion> buscarPorUsuarioNoLeidas(Long idUsuario) {
        return notificacionRepository.findByUsuarioIdUsuarioAndLeidoFalse(idUsuario);
    }

    public List<Notificacion> buscarPorSolicitud(Long idSolicitud) {
        return notificacionRepository.findBySolicitudIdSolicitud(idSolicitud);
    }

    public Notificacion marcarComoLeida(Long id) {
        Notificacion notificacion = buscarPorId(id);
        notificacion.setLeido(true);
        return notificacionRepository.save(notificacion);
    }

    public Notificacion actualizarNotificacion(Long id, Notificacion datos) {
        Notificacion notificacion = buscarPorId(id);
        notificacion.setMensaje(datos.getMensaje());
        notificacion.setLeido(datos.getLeido());
        return notificacionRepository.save(notificacion);
    }

    public void eliminarNotificacion(Long id) {
        Notificacion notificacion = buscarPorId(id);
        notificacionRepository.delete(notificacion);
    }
}
