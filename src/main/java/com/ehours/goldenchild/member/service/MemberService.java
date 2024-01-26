package com.ehours.goldenchild.member.service;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;

public interface MemberService {
    int signup(MemberSignUpReqDto memberSignUpReqDto);
    MemberLoginResDto login(MemberLoginReqDto memberLoginReqDto);
    int idCheck(String memberId);
    MemberDetailResDto memberDetail(int memberNo);
    int memberModify(MemberModifyReqDto memberModifyReqDto);
    int memberSignOut(int memberNo);
}
