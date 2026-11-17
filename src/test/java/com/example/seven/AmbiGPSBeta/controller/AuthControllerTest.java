package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.dto.LoginRequest;
import com.example.seven.AmbiGPSBeta.security.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Test
    @DisplayName("Login rechazado: devuelve HTTP 401 y no genera un JWT")
    void credencialesIncorrectasDevuelven401SinGenerarToken() {
        // Preparar: simular que el autenticador rechaza las credenciales.
        AuthenticationManager autenticador = mock(AuthenticationManager.class);
        JwtService jwtService = mock(JwtService.class);
        AuthController controlador = new AuthController(autenticador, jwtService);

        when(autenticador.authenticate(any()))
                .thenThrow(new BadCredentialsException("Credenciales incorrectas"));

        // Ejecutar: llamar al metodo real del controlador con datos de prueba.
        var respuesta = controlador.login(
                new LoginRequest("usuario1@ambigps.com", "clave-incorrecta"));

        // Verificar: si cambia el estado HTTP o se llama al servicio JWT, la prueba falla.
        assertEquals(HttpStatus.UNAUTHORIZED, respuesta.getStatusCode(),
                "Un login rechazado debe devolver HTTP 401");
        verifyNoInteractions(jwtService);
    }
}
