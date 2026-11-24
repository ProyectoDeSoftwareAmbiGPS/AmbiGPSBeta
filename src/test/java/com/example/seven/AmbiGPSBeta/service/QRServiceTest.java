package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.QR;
import com.example.seven.AmbiGPSBeta.repository.QRRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QRServiceTest {

    @Test
    @DisplayName("DarNelli - Un QR sin código recibe uno generado")
    void generarCodigoCuandoElQrNoLoTiene() {
        // Preparar el servicio y un QR sin código.
        QRRepository repositorio = mock(QRRepository.class);
        QRService servicio = new QRService(repositorio);
        QR qr = new QR();

        when(repositorio.save(qr)).thenReturn(qr);

        // Ejecutar el método real del servicio.
        QR resultado = servicio.crearQR(qr);

        // Verificar el código generado y la petición de guardado.
        assertNotNull(resultado);
        assertNotNull(resultado.getCodigo());
        assertFalse(resultado.getCodigo().isBlank());
        assertTrue(resultado.getCodigo().startsWith("QR-"));
        verify(repositorio).save(qr);

        System.out.println(
                "PRUEBA APROBADA - Se genero un codigo con prefijo QR- "
                + "y se solicito su guardado.");
    }

    @Test
    @DisplayName("DarNelli - Un QR con código conserva su valor")
    void conservarCodigoCuandoElQrYaLoTiene() {
        // Preparar el servicio y un QR con código.
        QRRepository repositorio = mock(QRRepository.class);
        QRService servicio = new QRService(repositorio);

        QR qr = new QR();
        qr.setCodigo("QR-PRUEBA-001");

        when(repositorio.save(qr)).thenReturn(qr);

        // Ejecutar el método real del servicio.
        QR resultado = servicio.crearQR(qr);

        // Verificar que conserva el código y solicita guardarlo.
        assertNotNull(resultado);
        assertEquals("QR-PRUEBA-001", resultado.getCodigo());
        verify(repositorio).save(qr);

        System.out.println(
                "PRUEBA APROBADA - Se conservo el codigo QR-PRUEBA-001 "
                + "y se solicito su guardado.");
    }
}
