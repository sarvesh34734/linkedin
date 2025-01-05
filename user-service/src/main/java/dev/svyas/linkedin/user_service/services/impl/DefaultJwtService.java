package dev.svyas.linkedin.user_service.services.impl;

import dev.svyas.linkedin.user_service.entities.User;
import dev.svyas.linkedin.user_service.services.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class DefaultJwtService implements JwtService {

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    @Override
    public String generateToken(User user) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .signWith(secretKey)
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("name",user.getName())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();
    }
}
