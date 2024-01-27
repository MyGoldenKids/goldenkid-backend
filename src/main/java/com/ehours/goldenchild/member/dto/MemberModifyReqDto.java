package com.ehours.goldenchild.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberModifyReqDto {
    private int memberNo;
    private String password;
    private String newPassword;
    private String nickname;
    private String phoneNumber;

}
