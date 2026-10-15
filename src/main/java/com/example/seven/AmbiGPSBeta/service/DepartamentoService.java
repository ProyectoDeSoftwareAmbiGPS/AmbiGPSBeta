package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.Departamento;
import com.example.seven.AmbiGPSBeta.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public Departamento crearDepartamento(Departamento departamento) {
        if (departamentoRepository.findByNombre(departamento.getNombre()).isPresent()) {
            throw new RuntimeException("El departamento " + departamento.getNombre() + " ya existe");
        }
        return departamentoRepository.save(departamento);
    }

    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }

    public Departamento buscarPorId(Integer id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Departamento no encontrado con id: " + id));
    }

    public Departamento buscarPorNombre(String nombre) {
        return departamentoRepository.findByNombre(nombre)
                .orElseThrow(() -> 
                    new RuntimeException("Departamento no encontrado: " + nombre));
    }

    public Departamento actualizarDepartamento(Integer id, Departamento datos) {
        Departamento departamento = buscarPorId(id);
        departamento.setNombre(datos.getNombre());
        return departamentoRepository.save(departamento);
    }

    public void eliminarDepartamento(Integer id) {
        Departamento departamento = buscarPorId(id);
        departamentoRepository.delete(departamento);
    }
}
