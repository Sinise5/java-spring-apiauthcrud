package com.example.apiauthcrud.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "QFp3ZalpBskMaBQhtFMitK7CXFwN2A5HBAnf3gJbO1U=";
    private static final long EXPIRATION_TIME = 86400000; // 1 hari

    // ✅ Membuat signing key dari secret key (Menggunakan HMAC SHA256)
    private Key getSigningKey1() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private SecretKey getSigningKey() {
    byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
    return new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
}

    // ✅ Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // ✅ Menggunakan `subject()`
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey()) // ✅ Tidak perlu `Jwts.SIG.HS256`, cukup `signWith(key)`
                .compact();
    }

    // ✅ Ekstrak username dari token
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey()) // ✅ Menggunakan `verifyWith()`
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // ✅ Validasi token JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSigningKey()) // ✅ Menggunakan `verifyWith()`
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
