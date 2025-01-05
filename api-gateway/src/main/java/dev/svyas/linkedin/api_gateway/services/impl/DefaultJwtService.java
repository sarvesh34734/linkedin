package dev.svyas.linkedin.api_gateway.services.impl;

import dev.svyas.linkedin.api_gateway.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class DefaultJwtService implements JwtService {

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    @Override
    public String getUserIdFromJwt(String jwtToken) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

        return claims.getSubject();
    }
}
