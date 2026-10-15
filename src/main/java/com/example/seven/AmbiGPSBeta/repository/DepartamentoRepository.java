package com.example.seven.AmbiGPSBeta.repository;

import com.example.seven.AmbiGPSBeta.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    Optional<Departamento> findByNombre(String nombre);
}
