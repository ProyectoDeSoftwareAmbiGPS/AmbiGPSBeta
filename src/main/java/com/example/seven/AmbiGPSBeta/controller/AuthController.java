package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.dto.AuthResponse;
import com.example.seven.AmbiGPSBeta.dto.LoginRequest;
import com.example.seven.AmbiGPSBeta.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            var autenticacion = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.correo(),
                            request.password()
                    )
            );

            UserDetails usuario = (UserDetails) autenticacion.getPrincipal();
            String token = jwtService.generarToken(usuario);

            return ResponseEntity.ok(
                    new AuthResponse(token, "Bearer")
            );

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Correo o contraseña incorrectos");
        }
    }
}