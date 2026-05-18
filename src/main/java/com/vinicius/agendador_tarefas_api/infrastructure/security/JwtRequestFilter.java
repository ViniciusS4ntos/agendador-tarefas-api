package com.vinicius.agendador_tarefas_api.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    // Construtor simplificado apenas com o que o filtro realmente usa agora
    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();
        String auth = request.getHeader("Authorization");

        System.out.println("=== FILTRO JWT ===");
        System.out.println("URI: " + path);
        System.out.println("Method: " + method);
        System.out.println("Auth header presente: " + (auth != null));
        System.out.println("Content-Type: " + request.getContentType());
        System.out.println("Auth atual no contexto ANTES: " + SecurityContextHolder.getContext().getAuthentication());

        // Atalho rápido: se for rota do Swagger, nem perde tempo processando o bloco de token
        if (path.contains("/swagger-ui") || path.contains("/v3/api-docs")) {
            chain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            final String token = authorizationHeader.substring(7);

            try {
                final String username = jwtUtil.extractUsername(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    if (jwtUtil.validateToken(token, username)) {

                        // Criamos a autoridade padrão com o prefixo correto do Spring Security
                        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");

                        // Monta o UserDetails em memória de forma stateless
                        UserDetails userDetails = org.springframework.security.core.userdetails.User
                                .withUsername(username)
                                .password("")
                                .authorities(Collections.singletonList(authority))
                                .build();

                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("Autenticação Stateless concluída para: " + username);
                    }
                }
            } catch (Exception e) {
                // Captura falhas de token malformado ou expirado sem quebrar o filtro pai do Spring
                System.out.println("Erro ao validar token no Agendador: " + e.getMessage());
            }
        }

        System.out.println("Auth atual no contexto DEPOIS: " + SecurityContextHolder.getContext().getAuthentication());

        chain.doFilter(request, response);
    }
}