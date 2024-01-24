package com.ehours.goldenchild.member.service;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int signup(MemberSignUpReqDto memberSignUpReqDto) {
        String pwd = memberSignUpReqDto.getPassword();
        String hashPwd = passwordEncoder.encode(pwd);
        memberSignUpReqDto.setPassword(hashPwd);

        return memberMapper.signup(memberSignUpReqDto);
    }

    @Override
    public MemberLoginResDto login(MemberLoginReqDto memberLoginReqDto) {
        String checkPwd = memberMapper.pwdCheck(memberLoginReqDto);
        if (passwordEncoder.matches(memberLoginReqDto.getPassword(), checkPwd)){
            return memberMapper.login(memberLoginReqDto);  // 로그인 성공
        } else{
            return null;  // 로그인 실패
        }
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
