package com.ehours.goldenchild.member.service;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        String checkPwd = memberMapper.LoginPwdCheck(memberLoginReqDto);
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
    public MemberDetailResDto memberDetail(int memberNo) {
        return memberMapper.memberDetail(memberNo);
    }

    @Override
    public int memberModify(MemberModifyReqDto memberModifyReqDto) {
        String pwd = memberModifyReqDto.getNewPassword();
        String hashPwd = passwordEncoder.encode(pwd);
        String storedPwd = memberMapper.ModifyPwdCheck(memberModifyReqDto);

        if(passwordEncoder.matches(memberModifyReqDto.getPassword(), storedPwd)){
            memberModifyReqDto.setPassword(hashPwd);
            return memberMapper.memberModify(memberModifyReqDto);
        } else{  // 비밀번호 인증을 통과하지 못하였을 경우
            return -1;
        }
    }

    @Override
    public int memberSignOut(int memberNo) {
        return memberMapper.memberSignOut(memberNo);
    }
}
