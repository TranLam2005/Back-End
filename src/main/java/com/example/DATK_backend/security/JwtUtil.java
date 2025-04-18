package com.example.DATK_backend.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {
    private final String SECRET_KEY = "thisIsASecretKeyThatIsLongEnough1234567890";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractUserName(String token) {
        return extractClaims(token).getSubject();
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName =extractUserName(token);
        return userName.equals(userDetails.getUsername()) && isTokenExpired(token);
    }
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
    public String generateToken (String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date((System.currentTimeMillis() + 1000*60*60)))
                .signWith(key)
                .compact();
    }
}
