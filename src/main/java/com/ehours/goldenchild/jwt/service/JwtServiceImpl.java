package com.ehours.goldenchild.jwt.service;

import com.ehours.goldenchild.jwt.dto.RefreshTokenDto;
import com.ehours.goldenchild.jwt.mapper.JwtMapper;
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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    @Value("${app.JWT_KEY}")
    public String JWT_KEY;
    @Value("${app.JWT_HEADER_A}")
    public String JWT_HEADER_A;
    @Value("${app.JWT_HEADER_R}")
    public String JWT_HEADER_R;
    @Value("${app.JWT_PREFIX}")
    public String JWT_PREFIX;

    private final JwtMapper jwtMapper;

    public List<String> generateToken(int no) {
        String accessToken = createAccessToken(no);
        String refreshToken = createRefreshToken(no);

        String tokenId = JWT_HEADER_R + no;

        RefreshTokenDto refreshTokenDto = new RefreshTokenDto(tokenId, refreshToken);
        // 이미 refreshToken이 저장되어있다면 업데이트
        if (jwtMapper.validateRefreshToken(tokenId) != null) {
            jwtMapper.UpdateToken(refreshTokenDto);
        } else {
            jwtMapper.createRefreshToken(refreshTokenDto);
        }

        List<String> tokenList = new ArrayList<>();
        tokenList.add(accessToken);
        tokenList.add(refreshToken);

        return tokenList;
    }

    /*
        토큰이 해당 사이트에서 발행한 토큰인지 만료되지 않았는지 점검
     */
    public boolean validateToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
        try {
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return !claims.getPayload().getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    /*
        현재 .MySql DB에 저장된 토큰 정보와 일치여부 점검
     */
    public boolean isRefreshTrue(int no, String token) {
        String tokenId = JWT_HEADER_R + no;

        String savedToken = jwtMapper.validateRefreshToken(tokenId);

        if (token.equals(savedToken)) {
            return true;
        } else {
            return false;
        }
    }

    /*
        인증된 토큰의 payload에서 userId를 추출해내는 Method
     */
    public int userCheck(String token) {
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

    private String createRefreshToken(int memberNo) {
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
