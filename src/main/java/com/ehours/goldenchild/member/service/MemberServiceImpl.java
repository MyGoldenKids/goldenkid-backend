package com.ehours.goldenchild.member.service;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{
    private MemberMapper memberMapper;

    @Override
    public int signup(MemberSignUpReqDto memberSignUpReqDto) {
        return memberMapper.signup(memberSignUpReqDto);
    }

    @Override
    public MemberLoginResDto login(MemberLoginReqDto memberLoginReqDto) {
        return memberMapper.login(memberLoginReqDto);
    }

    @Override
    public int idCheck(String memberId) {
        return memberMapper.idCheck(memberId);
    }

    @Override
    public MemberDetailResDto memberDetail(String memberId) {
        return memberMapper.memberDetail(memberId);
    }

    @Override
    public int memberModify(MemberModifyReqDto memberModifyReqDto) {
        return memberMapper.memberModify(memberModifyReqDto);
    }

    @Override
    public int memberSignOut(String memberId) {
        return memberMapper.memberSignOut(memberId);
    }
}
