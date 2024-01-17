package com.ehours.goldenchild.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {
    private int no;
    private int fileId;
    private String memberId;
    private String password;
    private String nickname;
    private boolean memberStatus;
    private String phoneNumber;
    private String joinedAt;
}
