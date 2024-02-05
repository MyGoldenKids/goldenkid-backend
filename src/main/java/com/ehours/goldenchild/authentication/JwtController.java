package com.ehours.goldenchild.authentication;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
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

    private final JwtGenerator jwtGenerator;
    private final JwtValidator jwtValidator;

    @PostMapping("/silent-refresh")
    public ResponseEntity<Map<String, Object>> regenerate(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (JWT_PREFIX.equals(cookie.getName())) {
                    refreshToken = cookie.getValue(); // refreshToken 값을 가져옵니다.
                    break;
                }
            }
        }

        if (refreshToken != null && jwtValidator.validateToken(refreshToken)){
            int no = jwtValidator.userCheck(refreshToken);
            String token = jwtGenerator.generateToken(response, no);

            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } else { // 쿠키가 만료되었거나 refreshToken이 만료되었을 경우
            throw new PreAuthenticatedCredentialsNotFoundException("no refresh token exist");
        }
    }
}
