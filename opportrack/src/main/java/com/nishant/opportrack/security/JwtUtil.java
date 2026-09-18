package com.nishant.opportrack.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Yeh secret key token ko sign/verify karne ke liye use hoti hai
   private final SecretKey secretKey = Keys.hmacShaKeyFor("MySecretKeyForJWTTokenGeneration123456".getBytes());

    // Token generate karna (login ke waqt)
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours valid
                .signWith(secretKey)
                .compact();
    }

    // Token se email nikalna (verify karte waqt)
    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Token valid hai ya nahi check karna
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}