package com.jtspringproject.JtSpringProject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private final String SECRET_KEY = "mysecretkey1234567890abcdefghijklmnopqrstuvwxyzmysecretkey1234567890abcdefghijklmnopqrstuvwxyz";
    private final long EXPIRATION_TIME = 86400000;   // 1 día en milisegundos

    // Generar token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Validar token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).build().parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extraer nombre de usuario del token
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
