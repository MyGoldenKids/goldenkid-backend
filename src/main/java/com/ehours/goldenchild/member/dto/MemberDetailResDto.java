package com.ehours.goldenchild.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDetailResDto {
    private String memberId;
    private String nickname;
    private String phoneNumber;
}
