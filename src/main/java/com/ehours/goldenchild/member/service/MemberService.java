package com.ehours.goldenchild.member.service;

import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;

public interface MemberService {
    int signup(MemberSignUpReqDto memberDto);
}
