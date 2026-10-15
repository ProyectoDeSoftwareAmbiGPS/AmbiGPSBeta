package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.Municipio;
import com.example.seven.AmbiGPSBeta.model.Departamento;
import com.example.seven.AmbiGPSBeta.repository.MunicipioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {

    private final MunicipioRepository municipioRepository;

    public MunicipioService(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    public Municipio crearMunicipio(Municipio municipio) {
        if (municipioRepository.findByNombre(municipio.getNombre()).isPresent()) {
            throw new RuntimeException("El municipio " + municipio.getNombre() + " ya existe");
        }
        return municipioRepository.save(municipio);
    }

    public List<Municipio> listarMunicipios() {
        return municipioRepository.findAll();
    }

    public Municipio buscarPorId(Integer id) {
        return municipioRepository.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Municipio no encontrado con id: " + id));
    }

    public Municipio buscarPorNombre(String nombre) {
        return municipioRepository.findByNombre(nombre)
                .orElseThrow(() -> 
                    new RuntimeException("Municipio no encontrado: " + nombre));
    }

    public List<Municipio> buscarPorDepartamento(Integer idDepartamento) {
        return municipioRepository.findByDepartamentoIdDepartamento(idDepartamento);
    }

    public Municipio actualizarMunicipio(Integer id, Municipio datos) {
        Municipio municipio = buscarPorId(id);
        municipio.setNombre(datos.getNombre());
        municipio.setDepartamento(datos.getDepartamento());
        return municipioRepository.save(municipio);
    }

    public void eliminarMunicipio(Integer id) {
        Municipio municipio = buscarPorId(id);
        municipioRepository.delete(municipio);
    }
}
