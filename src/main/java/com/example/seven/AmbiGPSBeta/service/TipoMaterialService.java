package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.TipoMaterial;
import com.example.seven.AmbiGPSBeta.repository.TipoMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoMaterialService {

    private final TipoMaterialRepository tipoMaterialRepository;

    public TipoMaterialService(TipoMaterialRepository tipoMaterialRepository) {
        this.tipoMaterialRepository = tipoMaterialRepository;
    }

    public TipoMaterial crearTipoMaterial(TipoMaterial tipoMaterial) {
        if (tipoMaterialRepository.findByNombre(tipoMaterial.getNombre()).isPresent()) {
            throw new RuntimeException("El tipo de material " + tipoMaterial.getNombre() + " ya existe");
        }
        return tipoMaterialRepository.save(tipoMaterial);
    }

    public List<TipoMaterial> listarTiposMaterial() {
        return tipoMaterialRepository.findAll();
    }

    public TipoMaterial buscarPorId(Integer id) {
        return tipoMaterialRepository.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Tipo de material no encontrado con id: " + id));
    }

    public TipoMaterial buscarPorNombre(String nombre) {
        return tipoMaterialRepository.findByNombre(nombre)
                .orElseThrow(() -> 
                    new RuntimeException("Tipo de material no encontrado: " + nombre));
    }

    public TipoMaterial actualizarTipoMaterial(Integer id, TipoMaterial datos) {
        TipoMaterial tipoMaterial = buscarPorId(id);
        tipoMaterial.setNombre(datos.getNombre());
        tipoMaterial.setDescripcion(datos.getDescripcion());
        return tipoMaterialRepository.save(tipoMaterial);
    }

    public void eliminarTipoMaterial(Integer id) {
        TipoMaterial tipoMaterial = buscarPorId(id);
        tipoMaterialRepository.delete(tipoMaterial);
    }
}
