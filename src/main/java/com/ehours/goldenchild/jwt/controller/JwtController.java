package com.ehours.goldenchild.jwt.controller;

import com.ehours.goldenchild.jwt.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Slf4j
public class JwtController {
    @Value("${app.JWT_PREFIX}")
    public String JWT_PREFIX;
    @Value("${app.JWT_REFRESH_EXPIRATION}")
    public String JWT_REFRESH_EXPIRATION;

    private final JwtService jwtService;

    @PostMapping("/silent-refresh")
    public ResponseEntity<Map<String, Object>> regenerate(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = null;
        Cookie refreshTokenCookie = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (JWT_PREFIX.equals(cookie.getName())) {
                    refreshToken = cookie.getValue(); // refreshToken 값을 가져옵니다.
                    refreshTokenCookie = cookie; // token의 쿠키 정보를 저장해놓습니다.
                    break;
                }
            }
        }

        if (refreshToken != null && jwtService.validateToken(refreshToken)){
            refreshTokenCookie.setMaxAge(0);  // 원래 가지고 있던 쿠키는 삭제해주어야합니다.
            response.addCookie(refreshTokenCookie);

            // 새로운 refresh token 발급 과정
            int no = jwtService.userCheck(refreshToken);
            List<String> tokens = jwtService.generateToken(no);

            Cookie cookie = new Cookie(JWT_PREFIX, tokens.get(1));
            cookie.setMaxAge(Integer.parseInt(JWT_REFRESH_EXPIRATION));
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);

            // access token 추가
            Map<String, Object> map = new HashMap<>();
            map.put("token", tokens.get(0));
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } else { // 쿠키가 만료되었거나 refreshToken이 만료되었을 경우
            throw new PreAuthenticatedCredentialsNotFoundException("no refresh token exist");
        }
    }
}
