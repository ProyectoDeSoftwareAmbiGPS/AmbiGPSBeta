package com.example.seven.AmbiGPSBeta.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey signingKey;
    private final long expirationMs;

    public JwtService(
            @Value("${security.jwt.secret-base64}") String secretBase64,
            @Value("${security.jwt.expiration-ms}") long expirationMs) {

        this.signingKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secretBase64)
        );
        this.expirationMs = expirationMs;
    }

    public String generarToken(UserDetails usuario) {
        Date ahora = new Date();
        Date vencimiento = new Date(ahora.getTime() + expirationMs);

        return Jwts.builder()
                .subject(usuario.getUsername())
                .issuedAt(ahora)
                .expiration(vencimiento)
                .signWith(signingKey)
                .compact();
    }

    public String obtenerCorreo(String token) {
        return obtenerClaims(token).getSubject();
    }

    public boolean tokenValido(String token, UserDetails usuario) {
        Claims claims = obtenerClaims(token);

        return claims.getSubject().equals(usuario.getUsername())
                && claims.getExpiration().after(new Date());
    }

    private Claims obtenerClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}