package com.yumka.demo.service;

import java.util.Date;

import com.yumka.demo.model.UserAuth;
import com.yumka.demo.model.UserRef;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import  org.springframework.beans.factory.annotation.Value;


@Service
public class JwtService {
  @Value("${jwt.secret}")
    private String SECRET;

    public String generateToken(UserRef user) {

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("role", "USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
