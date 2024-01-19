package com.ehours.goldenchild.member.service;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;

public interface MemberService {
    int signup(MemberSignUpReqDto memberSignUpReqDto);
    int idCheck(String memberId);
    MemberDetailResDto memberDetail(String memberId);
    int memberModify(MemberModifyReqDto memberModifyReqDto);
    int memberSignOut(String memberId);
}
