package com.ehours.goldenchild.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberLoginResDto {
    private int memberNo;
    private String nickname;
}
