package com.ehours.goldenchild.jwt.service;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public interface JwtService {
    List<String> generateToken(int no);
    boolean validateToken(String token);
    boolean isRefreshTrue(int no, String token);
    int userCheck(String token);
}
