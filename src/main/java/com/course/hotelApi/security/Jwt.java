package com.course.hotelApi.security;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class Jwt {

    private final String secret = "hgfxmout";

    public String createToken(String username) throws JWTCreationException {
        int expiration = 100000;
        return Jwts.builder()
                .setSubject(username)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch(TokenExpiredException e) {
            log.error("Token is expired: {}", e.getMessage());
        } catch(MalformedJwtException e) {
            log.error("Token is invalid: {}", e.getMessage());
        }
        return false;
    }
}
