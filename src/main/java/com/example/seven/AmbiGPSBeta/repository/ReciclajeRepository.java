package com.example.seven.AmbiGPSBeta.repository;

import com.example.seven.AmbiGPSBeta.model.Reciclaje;
import com.example.seven.AmbiGPSBeta.model.TipoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReciclajeRepository extends JpaRepository<Reciclaje, Long> {

    List<Reciclaje> findByTipo(TipoMaterial tipo);
}