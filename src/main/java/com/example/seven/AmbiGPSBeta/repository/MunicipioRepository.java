package com.example.seven.AmbiGPSBeta.repository;

import com.example.seven.AmbiGPSBeta.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    Optional<Municipio> findByNombre(String nombre);
    List<Municipio> findByDepartamentoIdDepartamento(Integer idDepartamento);
}
