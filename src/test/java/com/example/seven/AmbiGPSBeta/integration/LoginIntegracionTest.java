package com.example.seven.AmbiGPSBeta.integration;

import com.example.seven.AmbiGPSBeta.controller.AuthController;
import com.example.seven.AmbiGPSBeta.dto.AuthResponse;
import com.example.seven.AmbiGPSBeta.dto.LoginRequest;
import com.example.seven.AmbiGPSBeta.security.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.security.SecureRandom;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class LoginIntegracionTest {

    @Test
    @DisplayName("Alejandro - Login integra autenticación BCrypt y JWT real")
    void loginAutenticaYGeneraUnTokenValido() {
       
        BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();

        var usuario = User.withUsername("alejandro@example.test")
                .password(codificador.encode("ClavePrueba123"))
                .roles("USUARIO")
                .build();

       
        var usuarios = new InMemoryUserDetailsManager(usuario);
        var proveedor = new DaoAuthenticationProvider(usuarios);
        proveedor.setPasswordEncoder(codificador);

        var autenticador = new ProviderManager(proveedor);

        
        byte[] clave = new byte[32];
        new SecureRandom().nextBytes(clave);
        String claveBase64 = Base64.getEncoder().encodeToString(clave);

        JwtService jwtService = new JwtService(claveBase64, 60_000);
        AuthController controlador =
                new AuthController(autenticador, jwtService);

        
        var respuesta = controlador.login(
                new LoginRequest("alejandro@example.test", "ClavePrueba123"));

        
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());

        AuthResponse contenido = assertInstanceOf(
                AuthResponse.class, respuesta.getBody());

        assertEquals("Bearer", contenido.tipo());
        assertEquals(
                "alejandro@example.test",
                jwtService.obtenerCorreo(contenido.token()));

        assertTrue(jwtService.tokenValido(contenido.token(), usuario));

        System.out.println(
                "INTEGRACION APROBADA - Credenciales verificadas con BCrypt. "
                + "HTTP 200 y JWT firmado valido para el usuario.");
    }
}