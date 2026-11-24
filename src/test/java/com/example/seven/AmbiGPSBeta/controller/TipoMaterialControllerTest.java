package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.TipoMaterial;
import com.example.seven.AmbiGPSBeta.service.TipoMaterialService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TipoMaterialControllerTest {

    @Test
    @DisplayName("DarNelli - Consultar materiales devuelve 200 y la lista esperada")
    void listarMaterialesDevuelve200YLaListaEsperada() {
    
        TipoMaterialService servicio = mock(TipoMaterialService.class);
        TipoMaterialController controlador =
                new TipoMaterialController(servicio);

        TipoMaterial vidrio = new TipoMaterial(
                "Vidrio", "Botellas y frascos");

        List<TipoMaterial> materiales = List.of(vidrio);

        when(servicio.listarTiposMaterial()).thenReturn(materiales);

        
        var respuesta = controlador.listar();

        // Verificar el estado, los materiales y la llamada al servicio.
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(materiales, respuesta.getBody());
        verify(servicio).listarTiposMaterial();

        System.out.println(
                "PRUEBA APROBADA - Consulta de materiales: "
                + "HTTP 200 y lista esperada verificados.");
    }
}