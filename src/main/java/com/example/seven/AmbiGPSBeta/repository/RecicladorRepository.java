package com.example.seven.AmbiGPSBeta.repository;

import com.example.seven.AmbiGPSBeta.model.Reciclador;
import com.example.seven.AmbiGPSBeta.model.EstadoReciclador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecicladorRepository extends JpaRepository<Reciclador, Long> {

    List<Reciclador> findByDepartamentoAndMunicipio(
            String departamento,
            String municipio
    );

    List<Reciclador> findByEstado(EstadoReciclador estado);
}