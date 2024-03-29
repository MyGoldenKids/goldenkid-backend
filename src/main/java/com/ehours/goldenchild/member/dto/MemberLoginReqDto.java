package com.ehours.goldenchild.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberLoginReqDto {
    private String memberId;
    private String password;
}
