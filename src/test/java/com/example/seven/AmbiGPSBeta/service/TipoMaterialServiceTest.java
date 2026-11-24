package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.TipoMaterial;
import com.example.seven.AmbiGPSBeta.repository.TipoMaterialRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TipoMaterialServiceTest {

    @Test
    @DisplayName("DarNelli - Crear un material duplicado se rechaza")
    void crearMaterialDuplicadoSeRechaza() {
        // Preparar un material que ya existe en el repositorio simulado.
        TipoMaterialRepository repositorio =
                mock(TipoMaterialRepository.class);
        TipoMaterialService servicio = new TipoMaterialService(repositorio);

        TipoMaterial existente = new TipoMaterial(
                "Vidrio", "Botellas y frascos");
        TipoMaterial nuevo = new TipoMaterial(
                "Vidrio", "Otro material con el mismo nombre");

        when(repositorio.findByNombre("Vidrio"))
                .thenReturn(Optional.of(existente));

        // Ejecutar y comprobar que el servicio rechaza el duplicado.
        RuntimeException error = assertThrows(
                RuntimeException.class,
                () -> servicio.crearTipoMaterial(nuevo));

        assertEquals(
                "El tipo de material Vidrio ya existe",
                error.getMessage());

        verify(repositorio).findByNombre("Vidrio");
        verify(repositorio, never()).save(any());

        System.out.println(
                "PRUEBA APROBADA - Material duplicado rechazado "
                + "y guardado evitado.");
    }
}