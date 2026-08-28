package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.EstadoReciclador;
import com.example.seven.AmbiGPSBeta.model.Reciclador;
import com.example.seven.AmbiGPSBeta.repository.RecicladorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecicladorService {

    private final RecicladorRepository recicladorRepository;

    public RecicladorService(RecicladorRepository recicladorRepository) {
        this.recicladorRepository = recicladorRepository;
    }

    
    public Reciclador crearReciclador(Reciclador reciclador) {
        return recicladorRepository.save(reciclador);
    }

    
    public List<Reciclador> listarRecicladores() {
        return recicladorRepository.findAll();
    }

    
    public Reciclador buscarPorId(Long id) {
        return recicladorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Reciclador no encontrado con id: " + id
                        )
                );
    }

    
    public List<Reciclador> buscarPorUbicacion(
            String departamento,
            String municipio) {

        return recicladorRepository
                .findByDepartamentoAndMunicipio(
                        departamento,
                        municipio
                );
    }

    
    public List<Reciclador> buscarPorEstado(
            EstadoReciclador estado) {

        return recicladorRepository.findByEstado(estado);
    }

    
    public Reciclador actualizarReciclador(
            Long id,
            Reciclador datos) {

        Reciclador reciclador = buscarPorId(id);

        reciclador.setNombre(datos.getNombre());
        reciclador.setTelefono(datos.getTelefono());
        reciclador.setDepartamento(datos.getDepartamento());
        reciclador.setMunicipio(datos.getMunicipio());
        reciclador.setEstado(datos.getEstado());

        return recicladorRepository.save(reciclador);
    }

    
    public void eliminarReciclador(Long id) {

        Reciclador reciclador = buscarPorId(id);

        recicladorRepository.delete(reciclador);
    }
}