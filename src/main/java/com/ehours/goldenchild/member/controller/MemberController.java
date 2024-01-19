package com.ehours.goldenchild.member.controller;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        int retValue = memberService.idCheck(memberId);
        if (retValue == 1) return ResponseEntity.status(HttpStatus.ACCEPTED).body("회원가입 가능");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("아이디 중복");
    }

    @GetMapping("/detail")
    public ResponseEntity<MemberDetailResDto> memberDetail(String memberId) {
        MemberDetailResDto resValue = memberService.memberDetail(memberId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resValue);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> memberModify(MemberModifyReqDto memberModifyReqDto) {
        int resValue = memberService.memberModify(memberModifyReqDto);
        if (resValue == 1) return ResponseEntity.status(HttpStatus.ACCEPTED).body("수정 완료");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 실패");
    }

    @PutMapping("signout")
    public ResponseEntity<String> memberSignOut(String memberId) {
        int resValue = memberService.memberSignOut(memberId);
        if (resValue == 1) return ResponseEntity.status(HttpStatus.ACCEPTED).body("삭제 완료");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }

}
