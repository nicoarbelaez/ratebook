package com.nicoarbelaez.ratebook.session.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nicoarbelaez.ratebook.util.ConsolePrinter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final String PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest(request);
        if (token == null) {
            ConsolePrinter.info("No JWT token found, proceeding without authentication.");
            filterChain.doFilter(request, response);
            return;
        }

        ConsolePrinter.info("JWT token found: " + token);
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(PREFIX)) {
            ConsolePrinter.info("Authorization header found with Bearer prefix.");
            return authHeader.substring(PREFIX.length());
        }
        ConsolePrinter.info("No valid Authorization header found.");
        return null;
    }
}
