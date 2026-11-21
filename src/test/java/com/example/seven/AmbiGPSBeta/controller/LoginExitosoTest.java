package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.dto.AuthResponse;
import com.example.seven.AmbiGPSBeta.dto.LoginRequest;
import com.example.seven.AmbiGPSBeta.security.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoginExitosoTest {

    @Test
    @DisplayName("Alejandro - Login válido devuelve 200 y el token generado")
    void loginValidoDevuelve200YToken() {
        AuthenticationManager autenticador = mock(AuthenticationManager.class);
        JwtService jwtService = mock(JwtService.class);
        AuthController controlador =
                new AuthController(autenticador, jwtService);

        var usuario = User.withUsername("alejandro@example.test")
                .password("ClavePrueba123")
                .roles("USUARIO")
                .build();

        var autenticacion = new UsernamePasswordAuthenticationToken(
                usuario, null, usuario.getAuthorities());

        when(autenticador.authenticate(any())).thenReturn(autenticacion);
        when(jwtService.generarToken(usuario)).thenReturn("token-esperado");

        var respuesta = controlador.login(
                new LoginRequest("alejandro@example.test", "ClavePrueba123"));

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());

        AuthResponse contenido = assertInstanceOf(
                AuthResponse.class, respuesta.getBody());

        assertEquals("token-esperado", contenido.token());
        assertEquals("Bearer", contenido.tipo());
        verify(jwtService).generarToken(usuario);

        System.out.println("PRUEBA APROBADA - Login valido: HTTP 200, token y tipo Bearer verificados.");
    }
}
