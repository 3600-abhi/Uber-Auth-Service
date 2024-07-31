package com.uber.auth.Authentication.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${custom.jwt.expiry}")
    private int expiry;

    @Value("${custom.jwt.secret}")
    private String SECRET;

    public String createToken(Map<String, Object> payload, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry * 1000L); // * 1000 for Milli Second

        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                   .claims(payload)
                   .issuedAt(new Date(System.currentTimeMillis()))
                   .expiration(expiryDate)
                   .subject(username)
                   .signWith(key)
                   .compact();
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> mp = new HashMap<>();
        mp.put("name", "Abhishek Jaiswal");
        mp.put("email", "abhi@gmail.com");

        String token = createToken(mp, "hello_guyz");

        System.out.println("Token is : " + token);

    }
}
