package com.example.security.filter;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTGenerator {

    @Value("${jwt.Expiration}")
    private long JWTexpiration;

    @Value("${jwt.Secret}")
    private String jwtSecret;
    
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWTexpiration);

        SecretKey secretKey = getSecretKey();

        String token = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(expireDate)
                    .signWith(secretKey, SignatureAlgorithm.HS512)
                    .compact();

        return token;
    }

    private SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(jwtSecret);
        SecretKey originalKey = Keys.hmacShaKeyFor(decodedKey);
        return originalKey;
    }

    public String getUsernameFromJWT(String token) {
        SecretKey secretKey = getSecretKey();
        Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        return claims.getSubject();
                    
    }

    public boolean validateToken(String token) {
        System.out.println(token);
        SecretKey secretKey = getSecretKey();
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }

} 
