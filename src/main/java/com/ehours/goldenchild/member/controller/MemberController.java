package com.ehours.goldenchild.member.controller;

import com.ehours.goldenchild.member.dto.MemberDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(MemberSignUpReqDto memberDto) {
        int retValue = memberService.signup(memberDto);
        if (retValue == 1) return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료!");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패..");
    }
}
