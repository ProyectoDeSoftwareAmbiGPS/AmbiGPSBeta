package com.example.seven.AmbiGPSBeta.repository;

import com.example.seven.AmbiGPSBeta.model.Reciclaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReciclajeRepository extends JpaRepository<Reciclaje, Long> {
    List<Reciclaje> findBySolicitudIdSolicitud(Long idSolicitud);
}