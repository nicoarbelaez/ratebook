package com.nicoarbelaez.ratebook.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nicoarbelaez.ratebook.util.ConsolePrinter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Esta clase es un filtro de autenticación JWT que se ejecuta en cada solicitud
 * HTTP.
 * Verifica la validez del token JWT y autentica al usuario si el token es
 * válido.
 * Extiende OncePerRequestFilter para garantizar que se ejecute solo una vez por
 * solicitud.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Prefijo que identifica un token JWT en la cabecera Authorization.
    private final String PREFIX = "Bearer ";
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Filtra la solicitud HTTP para verificar la autenticidad del token JWT.
     * Si el token es válido, establece la autenticación en el contexto de seguridad
     * de Spring.
     *
     * @param request     Solicitud HTTP entrante.
     * @param response    Respuesta HTTP saliente.
     * @param filterChain Cadena de filtros que continúa el procesamiento de la
     *                    solicitud.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // Obtiene el token JWT de la solicitud.
        final String token = getTokenFromRequest(request);
        final String userEmail;

        // Si no se encuentra un token, continúa con la solicitud sin autenticación.
        if (token == null) {
            ConsolePrinter.info("No JWT token found, proceeding without authentication.");
            filterChain.doFilter(request, response);
            return;
        }

        // Extrae el email del usuario desde el token.
        userEmail = jwtService.getEmailFromToken(token);

        // Si el token es inválido o ha sido modificado, retorna un 401 y detiene el
        // procesamiento.
        if (userEmail == null) {
            ConsolePrinter.info("Invalid JWT token detected. Access denied.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Verifica si el usuario aún no está autenticado en el contexto de seguridad.
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Carga los detalles del usuario.
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            // Valida el token comparando el email y verificando que el token no esté
            // expirado.
            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                // Establece los detalles de autenticación en la solicitud.
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Autentica al usuario en el contexto de seguridad.
                SecurityContextHolder.getContext().setAuthentication(authToken);

                // Log para ver el contexto de autenticación en SecurityContextHolder.
                logSecurityContext();

                ConsolePrinter.info("User authenticated: " + userEmail);
            } else {
                ConsolePrinter.info("Invalid JWT token for user: " + userEmail);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        ConsolePrinter.info("JWT token found: " + token);
        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token JWT de la cabecera Authorization en la solicitud HTTP.
     *
     * @param request Solicitud HTTP entrante.
     * @return Token JWT sin el prefijo "Bearer ", o null si no está presente o es
     *         inválido.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(PREFIX)) {
            ConsolePrinter.info("Authorization header found with Bearer prefix.");
            return authHeader.substring(PREFIX.length());
        }
        ConsolePrinter.info("No valid Authorization header found.");
        return null;
    }

    /**
     * Muestra el contexto de seguridad actual en el log, incluyendo el usuario
     * autenticado
     * y sus roles. Esto ayuda a visualizar cómo `SecurityContextHolder` gestiona la
     * sesión.
     */
    private void logSecurityContext() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            ConsolePrinter.info("SecurityContext Authentication: "
                    + SecurityContextHolder.getContext().getAuthentication().toString());
            ConsolePrinter.info("Authenticated User: "
                    + SecurityContextHolder.getContext().getAuthentication().getName());
            ConsolePrinter.info("User Authorities: "
                    + SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        } else {
            ConsolePrinter.info("SecurityContext has no Authentication information.");
        }
    }
}
