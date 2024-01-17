package com.ehours.goldenchild.member.service;

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
    public int idCheck(String memberId) {
        return 0;
    }
}
