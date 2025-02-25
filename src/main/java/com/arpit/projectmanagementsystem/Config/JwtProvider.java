package com.arpit.projectmanagementsystem.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtProvider {

    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth)
    {
        String jwt = Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+ 8640000000L))
                .claim("email",auth.getName())
                .signWith(key)
                .compact();

        return  jwt;
    }

    public static  String getEmailFromToken(String jwt)
    {
        Claims claims = Jwts.parserBuilder().setSigningKey(key)
                .build().parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }
}
