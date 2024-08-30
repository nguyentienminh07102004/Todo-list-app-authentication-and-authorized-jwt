package org.PTITB22DCCN539.Sercurity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.PTITB22DCCN539.Model.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTGenToken {
    @Value("${expiration}")
    private Long expiration;
    @Value("${signerKey}")
    private String signerKey;

    public String generateToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .setSubject(user.getEmail())
                .signWith(Keys.hmacShaKeyFor(signerKey.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signerKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Date getExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signerKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public Boolean validationToken(UserDetails user, String token) {
        String email = user.getUsername();
        return email.equals(getEmailFromToken(token)) && getExpiration(token).after(new Date());
    }
}
