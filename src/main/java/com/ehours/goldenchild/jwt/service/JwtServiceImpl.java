package com.ehours.goldenchild.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService{
    @Value("${app.JWT_KEY}")
    public String JWT_KEY;
    @Value("${app.JWT_HEADER_A}")
    public String JWT_HEADER_A;
    @Value("${app.JWT_HEADER_R}")
    public String JWT_HEADER_R;
    @Value("${app.JWT_PREFIX}")
    public String JWT_PREFIX;

    public List<String> generateToken(int no){
        String accessToken = createAccessToken(no);
        String refreshToken = createRefreshToken(no);

        List<String> tokenList = new ArrayList<>();
        tokenList.add(accessToken);
        tokenList.add(refreshToken);

        return tokenList;
    }

    public boolean validateToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
        try {
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return !claims.getPayload().getExpiration().before(new Date());
        } catch (JwtException e){
            return false;
        }
    }

    public int userCheck(String token){
        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
        Jws<Claims> claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);

        return claims.getPayload().get("id", Integer.class);
    }

    private String createAccessToken(int memberNo) {
        Date date = new Date();
        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .issuer("GoldZZok")
                .claim("id", memberNo)
                .issuedAt(date)
                .expiration(new Date(date.getTime() + (1000 * 60 * 30)))  // 30분 설정
                .signWith(key).compact();
    }

    private String createRefreshToken(int memberNo){
        Date date = new Date();
        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .issuer("GoldZZok")
                .claim("id", memberNo)
                .issuedAt(date)
                .expiration(new Date(date.getTime() + (1000 * 60 * 60 * 24 * 7)))  // 7일 설정
                .signWith(key).compact();
    }
}
