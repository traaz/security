package com.example.demo.Service;

import com.example.demo.Models.UserInfModel;
import io.jsonwebtoken.Claims;
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

    public String getIdentityNumber(String token){
        return extractAllClaims(token).get("identityNumber").toString();
    }
    public String getUsername(String token){
        return extractAllClaims(token).get("username").toString();
    }

    public String getSurname(String token){
        return extractAllClaims(token).get("surname").toString();
    }
    public Integer getCityCode(String token){
        return (Integer) extractAllClaims(token).get("cityCode");
    }
    public Integer getInstitutionId(String token){
        return (Integer) extractAllClaims(token).get("institutionId");
    }
    public Date extractExpirationDate(String token){
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpirate(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey()) //verilen tokenın signature bu imzala mı olusturulmmus. gelen signature ile karşılaştır.
                .build()
                .parseClaimsJws(token)
                .getBody(); //payload
    }

}
