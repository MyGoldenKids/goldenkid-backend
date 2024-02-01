package com.ehours.goldenchild.sprint;

import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import com.ehours.goldenchild.sprint.dto.SprintModifyReqDto;
import com.ehours.goldenchild.sprint.dto.SprintResponseDto;
import com.ehours.goldenchild.sprint.dto.SprintStatusReqDto;
import com.ehours.goldenchild.sprint.service.SprintService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SprintTests {
    @Autowired
    private MemberService memberService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SprintService sprintService;


    private MemberLoginResDto login;

    @BeforeAll
    public void setUp() {
//        memberService = context.getBean(MemberService.class);
        String id = "test54321@kakao.com";
        String pw = "password1234";
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
    }

    @AfterAll
    public void deleteSetup() {
        jdbcTemplate.update("DELETE FROM member WHERE no = ?", login.getMemberNo());
    }
    @Test
    @Transactional
    void createSprint() {
        SprintCreateReqDto sprintCreateReqDto = SprintCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .sprintTitle("test")
                .startDate("2024-01-31")
                .endDate("2024-02-10")
                .build();

        int retValue = sprintService.createSprint(sprintCreateReqDto);
        log.info(sprintCreateReqDto.toString());
        System.out.println(retValue);
        Assertions.assertThat(retValue).isEqualTo(1);
    }

    @Test
    @Transactional
    void getSprint() {
        SprintCreateReqDto sprintCreateReqDto = SprintCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .sprintTitle("제목입니다.")
                .startDate("2024-01-31")
                .endDate("2024-02-10")
                .build();
        int val = sprintService.createSprint(sprintCreateReqDto);
        List<SprintResponseDto> sprintResponseDtos = sprintService.getSprintList(login.getMemberNo());
        log.info(sprintResponseDtos.toString());
        Assertions.assertThat(sprintResponseDtos).isNotNull();
    }

    @Test
    @Transactional
    void changeSprintStatus() {
        SprintCreateReqDto sprintCreateReqDto = SprintCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .sprintTitle("제목입니다.1")
                .startDate("2024-01-31")
                .endDate("2024-02-10")
                .build();
        int no = sprintService.createSprint(sprintCreateReqDto);
        List<SprintResponseDto> sprintResponseDtos = sprintService.getSprintList(login.getMemberNo());
        log.info(sprintResponseDtos.toString());
        System.out.println(no);
        System.out.println(sprintCreateReqDto.getSprintId());

        SprintStatusReqDto sprintStatusReqDto = SprintStatusReqDto.builder()
                .memberId(login.getMemberNo())
                .sprintStatus(false)
                .build();
        int retValue = sprintService.updateSprintStatus(sprintCreateReqDto.getSprintId(), sprintStatusReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
        sprintResponseDtos = sprintService.getSprintList(login.getMemberNo());
        log.info(sprintResponseDtos.toString());
    }

    @Test
    @Transactional
    void changeSprint() {
        SprintCreateReqDto sprintCreateReqDto = SprintCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .sprintTitle("제목입니다.1")
                .startDate("2024-01-31")
                .endDate("2024-02-10")
                .build();
        int no = sprintService.createSprint(sprintCreateReqDto);
        SprintModifyReqDto sprintModifyReqDto = SprintModifyReqDto.builder()
                .memberId(login.getMemberNo())
                .sprintTitle("수정한 제목")
                .startDate("2024-02-01")
                .endDate("2024-02-10")
                .build();
        log.info(sprintService.getSprintList(login.getMemberNo()).toString());
        int retValue = sprintService.updateSprint(sprintCreateReqDto.getSprintId(), sprintModifyReqDto);
        log.info(sprintService.getSprintList(login.getMemberNo()).toString());
        Assertions.assertThat(retValue).isEqualTo(1);
    }

    @Test
    @Transactional
    void deleteSprint() {
        SprintCreateReqDto sprintCreateReqDto = SprintCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .sprintTitle("제목입니다.1111")
                .startDate("2024-01-31")
                .endDate("2024-02-10")
                .build();
        sprintService.createSprint(sprintCreateReqDto);
        int sprId = sprintCreateReqDto.getSprintId();
        log.info(sprintService.getSprintList(login.getMemberNo()).toString());
        int retValue = sprintService.deleteSprint(sprId, login.getMemberNo());
        log.info(sprintService.getSprintList(login.getMemberNo()).toString());
        Assertions.assertThat(retValue).isEqualTo(1);
    }

}
