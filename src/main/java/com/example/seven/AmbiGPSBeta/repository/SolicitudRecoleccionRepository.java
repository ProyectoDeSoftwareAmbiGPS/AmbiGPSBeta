package com.example.seven.AmbiGPSBeta.repository;

import com.example.seven.AmbiGPSBeta.model.SolicitudRecoleccion;
import com.example.seven.AmbiGPSBeta.model.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRecoleccionRepository extends JpaRepository<SolicitudRecoleccion, Long> {
    List<SolicitudRecoleccion> findByUsuarioIdUsuario(Long idUsuario);
    List<SolicitudRecoleccion> findByRecicladorIdUsuario(Long idReciclador);
    List<SolicitudRecoleccion> findByEstado(EstadoSolicitud estado);
    List<SolicitudRecoleccion> findByTipoMaterialIdTipoMaterial(Integer idTipoMaterial);
}