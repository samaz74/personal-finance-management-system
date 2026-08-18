package com.accounting.app.security;

import com.accounting.app.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(User user){
        return Jwts.builder().subject(user.getEmail()).claim("ROLE_",user.getRole()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+expiration)).signWith(getSecretKey()).compact();
    }

    public String extractEmail(String token){
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public Boolean isTokenValid(String token){
        try {
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public LocalDateTime extractExpiration(String token){
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload().getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
