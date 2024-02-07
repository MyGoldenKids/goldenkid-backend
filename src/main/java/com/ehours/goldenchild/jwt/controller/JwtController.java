package com.ehours.goldenchild.jwt.controller;

import com.ehours.goldenchild.common.ResponseResource;
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
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class JwtController {
    @Value("${app.JWT_COOKIE_R}")
    public String JWT_R;
    @Value("${app.JWT_COOKIE_A}")
    public String JWT_A;
    @Value("${app.JWT_REFRESH_EXPIRATION}")
    public String JWT_REFRESH_EXPIRATION;
    @Value("${app.JWT_ACCESS_EXPIRATION}")
    public String JWT_ACCESS_EXPIRATION;

    private final JwtService jwtService;

    /*
        refresh token이 유효하다면 access token과 refresh token 재발급
     */
    @PostMapping("/silent-refresh")
    public ResponseEntity<Map<String, Object>> regenerate(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = null;
        Cookie refreshTokenCookie = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (JWT_R.equals(cookie.getName())) {
                    refreshToken = cookie.getValue(); // refreshToken 값을 가져옵니다.
                    refreshTokenCookie = cookie; // token의 쿠키 정보를 저장해놓습니다.
                    break;
                }
            }
        }

        if (refreshToken != null && jwtService.validateToken(refreshToken)){  // refresh token이 존재하고 우리가 발행한 토큰이라면
            int no = jwtService.userCheck(refreshToken);

            if(jwtService.isRefreshTrue(no, refreshToken)) {
                // 새로운 refresh token 발급 과정
                List<String> tokens = jwtService.generateToken(no);

                Cookie accessCookie = new Cookie(JWT_A, tokens.get(0));
                accessCookie.setMaxAge(Integer.parseInt(JWT_ACCESS_EXPIRATION));
                accessCookie.setHttpOnly(true);
                accessCookie.setPath("/");

                Cookie refreshCookie = new Cookie(JWT_R, tokens.get(1));
                refreshCookie.setMaxAge(Integer.parseInt(JWT_REFRESH_EXPIRATION));
                refreshCookie.setHttpOnly(true);
                refreshCookie.setPath("/");

                response.addCookie(refreshCookie);
                response.addCookie(accessCookie);

                // access token 추가
                Map<String, Object> map = new HashMap<>();
                map.put("message", "재발급 성공");
                return ResponseEntity.status(HttpStatus.OK).body(map);
            } else{
                log.info("modulated Token");
                throw new PreAuthenticatedCredentialsNotFoundException("modulated token");
            }
        } else { // 쿠키가 만료되었거나 refreshToken이 만료되었을 경우
            throw new PreAuthenticatedCredentialsNotFoundException("no refresh token exist");
        }
    }
}
