package com.ehours.goldenchild.filter;

import com.ehours.goldenchild.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Value("${app.JWT_PREFIX}")
    public String JWT_PREFIX;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = extractAccessToken(request);
        String refreshToken = extractRefreshToken(request);

        if (null != accessToken && jwtService.validateToken(accessToken)) {  // access token이 유효함
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtService.userCheck(accessToken), null, Collections.singletonList(authority)));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if (null != extractRefreshToken(request) && jwtService.validateToken(refreshToken)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());  // Refreshtoken이 유효함
        } else {
            authenticationFailureHandler(response);
        }

        filterChain.doFilter(request, response);
    }

    /*
    Access Token 유무를 확인하고 없다면 null return
     */
    private String extractAccessToken(HttpServletRequest request) throws IOException {
        String header = request.getHeader("Authorization");

        if (null != header && header.length() > 1) {
            return header;
        } else {
            return null;
        }
    }

    /*
    Refresh Token 유무를 확인하고 없다면 null return
     */
    private String extractRefreshToken(HttpServletRequest request) throws IOException {
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

        if (null != refreshToken && refreshToken.length() > 1) {
            return refreshToken;
        } else {
            return null;
        }
    }

    private void authenticationFailureHandler(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
    }
}
