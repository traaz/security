package com.example.demo.Service;

import com.example.demo.Models.UserInfModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;


@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private final long EXPIRATION_TIME = 1000 * 60 * 2; //2 dk

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(UserInfModel user) {
        return Jwts.builder()
                .claim("identityNumber",  user.getIdentity_number())
                .claim("username", user.getName())
                .claim("surname", user.getSurname())
                .claim("cityCode", user.getCityCode())
                .claim("institutionId", user.getInstitution_id())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
