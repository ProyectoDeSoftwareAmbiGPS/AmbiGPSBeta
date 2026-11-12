package com.example.seven.AmbiGPSBeta.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private static final String CLAVE_DE_PRUEBA = Base64.getEncoder().encodeToString(new byte[32]);

    private final JwtService servicio = new JwtService(CLAVE_DE_PRUEBA, 60_000);
    private final UserDetails usuario = usuario("ciudadano@example.test");

    @Test
    void tokenValidoConservaElCorreoDelUsuario() {
        String token = servicio.generarToken(usuario);

        assertEquals(usuario.getUsername(), servicio.obtenerCorreo(token));
        assertTrue(servicio.tokenValido(token, usuario));
    }

    @Test
    void tokenDeOtroUsuarioNoValida() {
        String token = servicio.generarToken(usuario);

        assertFalse(servicio.tokenValido(token, usuario("otro@example.test")));
    }

    @Test
    void tokenVencidoSeRechaza() {
        JwtService emisor = new JwtService(CLAVE_DE_PRUEBA, -60_000);
        String token = emisor.generarToken(usuario);

        assertThrows(ExpiredJwtException.class, () -> servicio.obtenerCorreo(token));
    }

    private UserDetails usuario(String correo) {
        return User.withUsername(correo).password("solo-para-pruebas").roles("USUARIO").build();
    }
}
