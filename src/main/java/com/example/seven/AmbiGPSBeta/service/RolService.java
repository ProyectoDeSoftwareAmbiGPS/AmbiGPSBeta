package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.Rol;
import com.example.seven.AmbiGPSBeta.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol crearRol(Rol rol) {
        if (rolRepository.findByNombre(rol.getNombre()).isPresent()) {
            throw new RuntimeException("El rol " + rol.getNombre() + " ya existe");
        }
        return rolRepository.save(rol);
    }

    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    public Rol buscarPorId(Integer id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Rol no encontrado con id: " + id));
    }

    public Rol buscarPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre)
                .orElseThrow(() -> 
                    new RuntimeException("Rol no encontrado: " + nombre));
    }

    public Rol actualizarRol(Integer id, Rol datos) {
        Rol rol = buscarPorId(id);
        rol.setNombre(datos.getNombre());
        rol.setDescripcion(datos.getDescripcion());
        return rolRepository.save(rol);
    }

    public void eliminarRol(Integer id) {
        Rol rol = buscarPorId(id);
        rolRepository.delete(rol);
    }
}
