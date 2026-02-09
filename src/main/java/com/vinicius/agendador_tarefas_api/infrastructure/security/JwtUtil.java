package com.vinicius.agendador_tarefas_api.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;

import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtil {

    // IMPORTANTE: para HS256 precisa ter pelo menos 256 bits (32 caracteres)
    private static final String SECRET_KEY = "sua-chave-secreta-super-segura-que-deve-ser-bem-longa";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Extrai todas as claims do token
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extrai o username (subject)
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Extrai a data de expiração
    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    // Verifica se o token está expirado
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Valida token
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }
}