package com.example.seven.AmbiGPSBeta.repository;

import com.example.seven.AmbiGPSBeta.model.QR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QRRepository extends JpaRepository<QR, Long> {
    Optional<QR> findBySolicitudIdSolicitud(Long idSolicitud);
    Optional<QR> findByCodigo(String codigo);
}
