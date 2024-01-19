package com.ehours.goldenchild.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberModifyReqDto {
    private String memberId;
    private String password;
    private String nickname;
    private String phoneNumber;

}
