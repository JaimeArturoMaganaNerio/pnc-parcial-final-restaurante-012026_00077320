package com.uca.pncparcialfinalrestaurante.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

// (Security)
@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String secret;

    @Value("${app.jwt-access-expiration-milliseconds}")
    private String accessExpiration;

    @Value("${app.jwt-refresh-expiration-milliseconds}")
    private String refreshExpiration;

    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    // Access token: short-lived token used on every protected request.
    public String generateAccessToken(Authentication authentication) {
        return buildToken(authentication.getName(), Long.parseLong(accessExpiration));
    }

    // Refresh token: long-lived token only for /api/auth/refresh.
    public String generateRefreshToken(Authentication authentication) {
        return buildToken(authentication.getName(), Long.parseLong(refreshExpiration));
    }

    private String buildToken(String username, long expirationMs) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Security: verifies signature + expiration; altered or expired tokens fail before business logic executes.
    public boolean validateToken(String token) {
        Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parse(token);
        return true;
    }
}

