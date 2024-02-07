package com.ehours.goldenchild.member.controller;

import com.ehours.goldenchild.common.ResponseResource;
import com.ehours.goldenchild.jwt.service.JwtService;
import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${app.JWT_PREFIX}")
    public String JWT_PREFIX;
    @Value("${app.JWT_COOKIE_R}")
    public String JWT_R;
    @Value("${app.JWT_COOKIE_A}")
    public String JWT_A;
    @Value("${app.JWT_REFRESH_EXPIRATION}")
    public String JWT_REFRESH_EXPIRATION;
    @Value("${app.JWT_ACCESS_EXPIRATION}")
    public String JWT_ACCESS_EXPIRATION;

    private final JwtService jwtService;
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody MemberSignUpReqDto memberSignUpReqDto) {
        int retValue = memberService.signup(memberSignUpReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "회원가입 완료");
        else return ResponseResource.handleError("회원가입 실패");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberLoginReqDto memberLoginReqDto, HttpServletResponse response) {
        MemberLoginResDto memberLoginResDto = memberService.login(memberLoginReqDto);
        if (memberLoginResDto != null) {
            List<String> tokens = jwtService.generateToken(memberLoginResDto.getMemberNo());

            Cookie accessCookie = new Cookie(JWT_A, tokens.get(0));
            accessCookie.setMaxAge(Integer.parseInt(JWT_ACCESS_EXPIRATION));
            accessCookie.setHttpOnly(true);
            accessCookie.setPath("/");

            Cookie refreshCookie = new Cookie(JWT_R, tokens.get(1));
            refreshCookie.setMaxAge(Integer.parseInt(JWT_REFRESH_EXPIRATION));
            refreshCookie.setHttpOnly(true);
            refreshCookie.setPath("/");

            response.addCookie(refreshCookie);
            response.addCookie(accessCookie);

            return ResponseResource.handleSuccess(memberLoginResDto, "로그인 성공");
        } else return ResponseResource.handleError("로그인 실패");
    }

    @PostMapping("/idcheck/{memberId}")
    public ResponseEntity<Map<String, Object>> idCheck(@PathVariable String memberId) {
        int retValue = memberService.idCheck(memberId);
        if (retValue != 1) return ResponseResource.handleSuccess(1, "회원가입 가능");
        else return ResponseResource.handleSuccess(0, "회원가입 불가능");
    }

    @GetMapping("/detail/{memberNo}")
    public ResponseEntity<Map<String, Object>> memberDetail(@PathVariable int memberNo) {
        MemberDetailResDto memberDetailResDto = memberService.memberDetail(memberNo);
        if (memberDetailResDto != null)
            return ResponseResource.handleSuccess(memberDetailResDto, "조회 성공");
        else return ResponseResource.handleError("조회 실패");
    }

    @PutMapping("/modify")
    public ResponseEntity<Map<String, Object>> memberModify(@RequestBody MemberModifyReqDto memberModifyReqDto) {
        int resValue = memberService.memberModify(memberModifyReqDto);
        if (resValue == 1) return ResponseResource.handleSuccess(memberModifyReqDto.getMemberNo(), "수정 완료");
        else if (resValue == -1) return ResponseResource.handleError("비밀번호 오류");
        else return ResponseResource.handleError("수정 실패");
    }

    @PutMapping("/signout/{memberNo}")
    public ResponseEntity<Map<String, Object>> memberSignOut(@PathVariable int memberNo) {
        int resValue = memberService.memberSignOut(memberNo);
        if (resValue == 1) return ResponseResource.handleSuccess(memberNo, "삭제 완료");
        else return ResponseResource.handleError("삭제 실패");
    }

}
