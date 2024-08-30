package com.App.Yogesh.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtProvider {

    // Generate a secure key with at least 256-bit length
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // HS256 will use a 256-bit key

    public static String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuer("Yogesh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 3600000)) // 1 hour expiry
                .claim("email", auth.getName())
                .claim("roles", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .signWith(key)
                .compact();
    }

    public static String getEmailFromJwtToken(String jwt) {
        jwt = jwt.substring(7); // Assuming "Bearer " prefix
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return String.valueOf(claims.get("email"));
    }

    public static List<GrantedAuthority> getAuthoritiesFromJwtToken(String jwt) {
        jwt = jwt.substring(7); // Assuming "Bearer " prefix
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        @SuppressWarnings("unchecked")
        List<String> roles = claims.get("roles", List.class);

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
