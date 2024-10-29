package com.nicoarbelaez.ratebook.session.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nicoarbelaez.ratebook.util.ConsolePrinter;

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
}
