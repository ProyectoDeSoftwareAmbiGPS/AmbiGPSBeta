package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.Historial;
import com.example.seven.AmbiGPSBeta.repository.HistorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialService {

    private final HistorialRepository historialRepository;

    public HistorialService(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public Historial crearHistorial(Historial historial) {
        return historialRepository.save(historial);
    }

    public List<Historial> listarHistorial() {
        return historialRepository.findAll();
    }

    public Historial buscarPorId(Long id) {
        return historialRepository.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Historial no encontrado con id: " + id));
    }

    public List<Historial> buscarPorUsuario(Long idUsuario) {
        return historialRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public Historial actualizarHistorial(Long id, Historial datos) {
        Historial historial = buscarPorId(id);
        historial.setFecha(datos.getFecha());
        historial.setCantidad(datos.getCantidad());
        historial.setTipoMaterial(datos.getTipoMaterial());
        return historialRepository.save(historial);
    }

    public void eliminarHistorial(Long id) {
        Historial historial = buscarPorId(id);
        historialRepository.delete(historial);
    }
}
