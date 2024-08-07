package com.danny.Online.food.store.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtProvider {

    private final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth) {
        String roles = getAuthoritiesAsString(auth.getAuthorities());
        Instant now = Instant.now();
        Instant expirationTime = now.plusMillis(Long.parseLong(JwtConstant.EXPIRATION_TIME));

        return Jwts.builder()
                .issuedAt(Date.from(now))
                .expiration(Date.from(expirationTime))
                .claim("email", auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();
    }

    public String getEmailFromJWT(String jwt) {
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }
        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
        return claims.get("email", String.class);
    }

    private String getAuthoritiesAsString(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null || authorities.isEmpty()) {
            return "";
        }
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
}
