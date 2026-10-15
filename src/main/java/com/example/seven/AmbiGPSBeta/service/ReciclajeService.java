package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.Reciclaje;
import com.example.seven.AmbiGPSBeta.repository.ReciclajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReciclajeService {

    private final ReciclajeRepository reciclajeRepository;

    public ReciclajeService(ReciclajeRepository reciclajeRepository) {
        this.reciclajeRepository = reciclajeRepository;
    }

    public Reciclaje crearReciclaje(Reciclaje reciclaje) {
        return reciclajeRepository.save(reciclaje);
    }

    public List<Reciclaje> listarReciclajes() {
        return reciclajeRepository.findAll();
    }

    public Reciclaje buscarPorId(Long id) {
        return reciclajeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Reciclaje no encontrado con id: " + id
                        )
                );
    }

    public List<Reciclaje> buscarPorSolicitud(Long idSolicitud) {
        return reciclajeRepository.findBySolicitudIdSolicitud(idSolicitud);
    }

    public Reciclaje actualizarReciclaje(
            Long id,
            Reciclaje datos) {

        Reciclaje reciclaje = buscarPorId(id);

        reciclaje.setPesoKg(datos.getPesoKg());
        reciclaje.setDescripcion(datos.getDescripcion());

        return reciclajeRepository.save(reciclaje);
    }

    public void eliminarReciclaje(Long id) {

        Reciclaje reciclaje = buscarPorId(id);

        reciclajeRepository.delete(reciclaje);
    }
}