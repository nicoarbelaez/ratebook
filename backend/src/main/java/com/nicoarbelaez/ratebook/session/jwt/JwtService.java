package com.nicoarbelaez.ratebook.session.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nicoarbelaez.ratebook.util.ConsolePrinter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String getJwtToken(UserDetails user) {
        ConsolePrinter.info("JWT Service: Generating token for user " + user.getUsername());
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        ConsolePrinter.info("JWT Service: Adding claims and signing token for user " + user.getUsername());
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getEmailFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        if (userDetails == null || userDetails.getUsername() == null || token == null || token.isEmpty()) {
            ConsolePrinter.info("JWT Service: User details, username, or token is null/empty, cannot validate token.");
            return false;
        }
        final String userEmail = getEmailFromToken(token);
        return (userEmail != null && userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        if (token == null || token.isEmpty()) {
            ConsolePrinter.info("JWT Service: Token is null or empty, cannot retrieve claims.");
            return null;
        }
        final Claims claims = getAllClaims(token);
        return claims != null ? claimsResolver.apply(claims) : null;
    }

    private Claims getAllClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (io.jsonwebtoken.security.SignatureException e) {
            ConsolePrinter.info("JWT Service: Invalid signature for token - possible tampering detected.");
            return null;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            ConsolePrinter.info("JWT Service: Token has expired.");
            return null;
        } catch (Exception e) {
            ConsolePrinter.info("JWT Service: Unexpected error while parsing token - " + e.getMessage());
            return null;
        }
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
