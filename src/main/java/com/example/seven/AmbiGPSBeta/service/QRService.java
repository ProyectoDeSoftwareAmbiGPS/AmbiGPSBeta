package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.QR;
import com.example.seven.AmbiGPSBeta.repository.QRRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QRService {

    private final QRRepository qrRepository;

    public QRService(QRRepository qrRepository) {
        this.qrRepository = qrRepository;
    }

    public QR crearQR(QR qr) {
        if (qr.getCodigo() == null || qr.getCodigo().isEmpty()) {
            qr.setCodigo(generarCodigoQR());
        }
        return qrRepository.save(qr);
    }

    public List<QR> listarQRs() {
        return qrRepository.findAll();
    }

    public QR buscarPorId(Long id) {
        return qrRepository.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("QR no encontrado con id: " + id));
    }

    public QR buscarPorSolicitud(Long idSolicitud) {
        return qrRepository.findBySolicitudIdSolicitud(idSolicitud)
                .orElseThrow(() -> 
                    new RuntimeException("QR no encontrado para la solicitud: " + idSolicitud));
    }

    public QR buscarPorCodigo(String codigo) {
        return qrRepository.findByCodigo(codigo)
                .orElseThrow(() -> 
                    new RuntimeException("QR no encontrado con código: " + codigo));
    }

    public QR actualizarQR(Long id, QR datos) {
        QR qr = buscarPorId(id);
        qr.setCodigo(datos.getCodigo());
        return qrRepository.save(qr);
    }

    public void eliminarQR(Long id) {
        QR qr = buscarPorId(id);
        qrRepository.delete(qr);
    }

    private String generarCodigoQR() {
        return "QR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
