package com.ehours.goldenchild.member.controller;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(MemberSignUpReqDto memberSignUpReqDto) {
        int retValue = memberService.signup(memberSignUpReqDto);
        if (retValue == 1) return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료!");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패..");
    }

    @PostMapping("/idcheck")
    public ResponseEntity<String> idCheck(String memberId) {
        String retValue = memberService.idCheck(memberId);
        if (retValue.equals(memberId)) return ResponseEntity.status(HttpStatus.ACCEPTED).body("회원가입 가능");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("아이디 중복");
    }

    @GetMapping("/detail")
    public ResponseEntity<MemberDetailResDto> memberDetail(String memberId) {
        MemberDetailResDto resValue = memberService.memberDetail(memberId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resValue);
    }
}
