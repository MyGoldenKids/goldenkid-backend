package com.ehours.goldenchild.member.controller;

import com.ehours.goldenchild.common.ResponseResource;
import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody MemberSignUpReqDto memberSignUpReqDto) {
        int retValue = memberService.signup(memberSignUpReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "회원가입 완료");
        else return ResponseResource.handleError("회원가입 실패");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberLoginReqDto memberLoginReqDto) {
        MemberLoginResDto memberLoginResDto = memberService.login(memberLoginReqDto);
        if (memberLoginResDto != null) return ResponseResource.handleSuccess(memberLoginResDto, "로그인 성공");
        else return ResponseResource.handleError("로그인 실패");
    }

    @PostMapping("/idcheck/{memberId}")
    public ResponseEntity<Map<String, Object>> idCheck(@PathVariable String memberId) {
        int retValue = memberService.idCheck(memberId);
        if (retValue != 1) return ResponseResource.handleSuccess(1, "회원가입 가능");
        else return ResponseResource.handleSuccess(0, "회원가입 불가능");
    }

    @GetMapping("/detail/{memberId}")
    public ResponseEntity<Map<String, Object>> memberDetail(@PathVariable String memberId) {
        MemberDetailResDto memberDetailResDto = memberService.memberDetail(memberId);
        if (memberDetailResDto != null) return ResponseResource.handleSuccess(memberDetailResDto, "조회 성공");
        else return ResponseResource.handleError("조회 실패");
    }

    @PutMapping("/modify")
    public ResponseEntity<Map<String, Object>> memberModify(@RequestBody MemberModifyReqDto memberModifyReqDto) {
        int resValue = memberService.memberModify(memberModifyReqDto);
        if (resValue == 1) return ResponseResource.handleSuccess(resValue, "수정 완료");
        else return ResponseResource.handleError("수정 실패");
    }

    @PutMapping("signout/{memberId}")
    public ResponseEntity<Map<String, Object>> memberSignOut(@PathVariable String memberId) {
        int resValue = memberService.memberSignOut(memberId);
        if (resValue == 1) return ResponseResource.handleSuccess(resValue, "삭제 완료");
        else return ResponseResource.handleError("삭제 실패");
    }

}
