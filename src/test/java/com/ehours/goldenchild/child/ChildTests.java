package com.ehours.goldenchild.child;

import com.ehours.goldenchild.child.dto.ChildDetailResDto;
import com.ehours.goldenchild.child.dto.ChildModifyReqDto;
import com.ehours.goldenchild.child.dto.ChildRegisterReqDto;
import com.ehours.goldenchild.child.service.ChildService;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChildTests {
    @Autowired
    private MemberService memberService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ChildService childService;
    private ChildRegisterReqDto childTest;

    private MemberLoginResDto login;
    private String id = "test54321@kakao.com";
    private String pw = "password1234";
    @BeforeAll
    public void setUp() {
        MemberSignUpReqDto memberSignUpReqDto = MemberSignUpReqDto.builder()
                .memberId(id)
                .password(pw)
                .nickname("카카오")
                .phoneNumber("010-1234-5678")
                .build();
        memberService.signup(memberSignUpReqDto);
        MemberLoginReqDto memberLoginReqDto = MemberLoginReqDto.builder()
                .memberId(id)
                .password(pw)
                .build();
        login = memberService.login(memberLoginReqDto);

        childTest = ChildRegisterReqDto.builder()
                .childName("금쪽이")
                .childBirth("2022-10-01")
                .childGender(false)
                .memberId(login.getMemberNo())
                .build();
        int retValue = childService.registerChild(childTest);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(childTest.toString());
    }

    @AfterAll
    public void deleteSetup() {
        jdbcTemplate.update("DELETE FROM member WHERE no = ?", login.getMemberNo());
    }

    @Test
    @Transactional
    void registerChild() {
        ChildRegisterReqDto childRegisterReqDto = ChildRegisterReqDto.builder()
                .childName("금쪽이")
                .childBirth("2022-10-01")
                .childGender(false)
                .memberId(login.getMemberNo())
                .build();
        int retValue = childService.registerChild(childRegisterReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(childRegisterReqDto.toString());
    }

    @Test
    @Transactional
    void detailChild() {
        ChildDetailResDto retValue = childService.detailChild(childTest.getChildId());
        log.info(retValue.toString());
    }

    @Test
    @Transactional
    void modifyChild() {
        ChildModifyReqDto childModifyReqDto = ChildModifyReqDto.builder()
                .childId(childTest.getChildId())
                .childName("난가?")
                .childGender(false)
                .childBirth("2024-01-01")
                .build();
        int retValue = childService.modifyChild(childModifyReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(childModifyReqDto.toString());
    }

    @Test
    @Transactional
    void deleteChild() {
        int retValue = childService.deleteChild(childTest.getChildId());
        Assertions.assertThat(retValue).isEqualTo(1);
    }
}
