package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.dto.LoginRequest;
import com.example.seven.AmbiGPSBeta.security.JwtService;
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
    void credencialesIncorrectasDevuelven401SinGenerarToken() {
        AuthenticationManager autenticador = mock(AuthenticationManager.class);
        JwtService jwtService = mock(JwtService.class);
        AuthController controlador = new AuthController(autenticador, jwtService);

        when(autenticador.authenticate(any()))
                .thenThrow(new BadCredentialsException("Credenciales incorrectas"));

        var respuesta = controlador.login(
                new LoginRequest("usuario1@ambigps.com", "clave-incorrecta"));

        assertEquals(HttpStatus.UNAUTHORIZED, respuesta.getStatusCode());
        verifyNoInteractions(jwtService);
    }
}
