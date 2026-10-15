package com.example.seven.AmbiGPSBeta.repository;

import com.example.seven.AmbiGPSBeta.model.Usuario;
import com.example.seven.AmbiGPSBeta.model.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
    List<Usuario> findByEstado(EstadoUsuario estado);
    List<Usuario> findByRolIdRol(Integer idRol);
    List<Usuario> findByMunicipioIdMunicipio(Integer idMunicipio);
}