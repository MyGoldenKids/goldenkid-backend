package com.ehours.goldenchild.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RefreshTokenDto {
    private String tokenId;
    private String refreshToken;
}
