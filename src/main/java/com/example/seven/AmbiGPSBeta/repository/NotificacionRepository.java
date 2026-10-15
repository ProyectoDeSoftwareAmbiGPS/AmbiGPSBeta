package com.example.seven.AmbiGPSBeta.repository;

import com.example.seven.AmbiGPSBeta.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUsuarioIdUsuario(Long idUsuario);
    List<Notificacion> findByUsuarioIdUsuarioAndLeidoFalse(Long idUsuario);
    List<Notificacion> findBySolicitudIdSolicitud(Long idSolicitud);
}
