package com.example.seven.AmbiGPSBeta.repository;

import com.example.seven.AmbiGPSBeta.model.SolicitudRecoleccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudRecoleccionRepository
        extends JpaRepository<SolicitudRecoleccion, Long> {

    List<SolicitudRecoleccion> findByUsuarioIdUsuario(Long idUsuario);

    List<SolicitudRecoleccion> findByRecicladorIdReciclador(Long idReciclador);

    List<SolicitudRecoleccion> findByEstado(String estado);
}