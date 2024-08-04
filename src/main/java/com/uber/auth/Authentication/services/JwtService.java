package com.uber.auth.Authentication.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${custom.jwt.expiry}")
    private int expiry;

    @Value("${custom.jwt.secret}")
    private String SECRET;

    public String createToken(Map<String, Object> payload, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry * 1000L); // * 1000 for Milli Second

        return Jwts.builder()
                   .claims(payload)
                   .issuedAt(new Date(System.currentTimeMillis()))
                   .expiration(expiryDate)
                   .subject(email)
                   .signWith(getSignKey())
                   .compact();
    }

    public String createToken(String email) {
        return createToken(new HashMap<>(), email);
    }

    public boolean validateToken(String token, String email) {
        String emailInsideToken = extractEmail(token);
        return emailInsideToken.equalsIgnoreCase(email) && !isTokenExpired(token);
    }

    public Claims extractAllPayloads(String token) {
        return Jwts.parser()
                   .setSigningKey(getSignKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllPayloads(token);
        return claimsResolver.apply(claims);
    }

    public Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    // for any random key, field which we set in payload
    public Object extractPayload(String token, String payloadKey) {
        Claims claims = extractAllPayloads(token);
        return (Object) claims.get(payloadKey);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> mp = new HashMap<>();
        mp.put("name", "Abhishek Jaiswal");
        mp.put("phoneNumber", "5658");

        String token = createToken(mp, "abhi@gmail.com");

        System.out.println("phoneNumber : " + extractPayload(token, "phoneNumber"));

    }
}
