package com.ehours.goldenchild.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtGenerator {
    @Value("${app.JWT_KEY}")
    public String JWT_KEY;
    @Value("${app.JWT_HEADER_A}")
    public String JWT_HEADER_A;
    @Value("${app.JWT_HEADER_R}")
    public String JWT_HEADER_R;
    @Value("${app.JWT_PREFIX}")
    public String JWT_PREFIX;
    @Value("${app.JWT_REFRESH_EXPIRATION}")
    public String JWT_REFRESH_EXPIRATION;

    public String generateToken(HttpServletResponse response, int no){
        String accessToken = createAccessToken(no);
        String refreshToken = createRefreshToken(no);

        Cookie cookie = new Cookie(JWT_PREFIX, refreshToken);
        cookie.setMaxAge(Integer.parseInt(JWT_REFRESH_EXPIRATION));
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        return accessToken;
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