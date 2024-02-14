package com.ehours.goldenchild.diary;

import com.ehours.goldenchild.child.dto.ChildRegisterReqDto;
import com.ehours.goldenchild.child.service.ChildService;
import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import com.ehours.goldenchild.diary.dto.DiaryDateReqDto;
import com.ehours.goldenchild.diary.dto.DiaryDetailResDto;
import com.ehours.goldenchild.diary.dto.DiaryResDto;
import com.ehours.goldenchild.diary.dto.DiarySubmitReqDto;
import com.ehours.goldenchild.diary.dto.DiaryUpdateReqDto;
import com.ehours.goldenchild.diary.service.DiaryService;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
public class DiaryTests {
    @Autowired
    private MemberService memberService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    DiaryService diaryService;
    @Autowired
    ChildService childService;

    private MemberLoginResDto login;
    private String id = "test54321@kakao.com";
    private String pw = "password1234";

    private DiaryCreateReqDto diaryTest;
    private ChildRegisterReqDto childTest;

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

        diaryTest = DiaryCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .childId(childTest.getChildId())
                .build();
        diaryService.createDiary(diaryTest);
    }

    @AfterAll
    public void deleteSetup() {
        jdbcTemplate.update("DELETE FROM member WHERE no = ?", login.getMemberNo());
    }

    @Test
    @Transactional
    void createDiary() {
        DiaryCreateReqDto diaryCreateReqDto = DiaryCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .childId(childTest.getChildId())
                .build();
        int retValue = diaryService.createDiary(diaryCreateReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(diaryCreateReqDto.toString());
    }

    @Test
    @Transactional
    void submitDiary() {
        DiarySubmitReqDto diarySubmitReqDto = DiarySubmitReqDto.builder()
                .memberId(login.getMemberNo())
                .diaryId(diaryTest.getDiaryId())
                .diaryTitle("우리애기")
                .diaryContent("내용5")
                .diaryReview("???")
                .build();
        int retValue = diaryService.submitDiary(diarySubmitReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(diarySubmitReqDto.toString());
    }

    @Test
    @Transactional
    void deleteDiary() {
        int retValue = diaryService.deleteDiary(diaryTest.getDiaryId());
        Assertions.assertThat(retValue).isEqualTo(1);
    }

    @Test
    @Transactional
    void updateDiary() {
        DiaryUpdateReqDto diaryUpdateReqDto = DiaryUpdateReqDto.builder()
                .diaryTitle("우리 아이")
                .diaryContent("이게 맞나여?")
                .diaryReview("")
                .memberId(login.getMemberNo())
                .build();
        diaryUpdateReqDto.setDiaryId(diaryTest.getDiaryId());
        log.info(diaryUpdateReqDto.toString());
        int retValue = diaryService.updateDiary(diaryUpdateReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(diaryUpdateReqDto.toString());
    }

    @Test
    @Transactional
    void listDiary() {
        List<DiaryResDto> diaryListResDto = diaryService.listDiary(login.getMemberNo(), 5, 0);
        log.info(diaryListResDto.toString());
        Assertions.assertThat(diaryListResDto).isNotNull();
    }

    @Test
    @Transactional
    void detailDiary() {
        DiaryDetailResDto diaryDetailResDto = diaryService.detailDiary(diaryTest.getDiaryId());
        log.info(diaryDetailResDto.toString());
        Assertions.assertThat(diaryDetailResDto).isNotNull();
    }

    @Test
    @Transactional
    void listDiaryDate() {
        DiaryCreateReqDto diaryTest1 = DiaryCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .childId(childTest.getChildId())
                .build();
        diaryService.createDiary(diaryTest1);
        DiarySubmitReqDto diarySubmitReqDto1 = DiarySubmitReqDto.builder()
                .memberId(login.getMemberNo())
                .diaryId(diaryTest1.getDiaryId())
                .diaryTitle("응애응애")
                .diaryContent("내용5")
                .diaryReview("???")
                .build();
        diaryService.submitDiary(diarySubmitReqDto1);

        DiaryCreateReqDto diaryTest2 = DiaryCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .childId(childTest.getChildId())
                .build();
        diaryService.createDiary(diaryTest2);
        DiarySubmitReqDto diarySubmitReqDto2 = DiarySubmitReqDto.builder()
                .memberId(login.getMemberNo())
                .diaryId(diaryTest2.getDiaryId())
                .diaryTitle("아 몰랑")
                .diaryContent("내용5")
                .diaryReview("???")
                .build();
        diaryService.submitDiary(diarySubmitReqDto2);


        log.info(diaryService.detailDiary(diarySubmitReqDto2.getDiaryId()).toString());
        DiaryDateReqDto diaryDateReqDto = DiaryDateReqDto.builder()
                .memberId(login.getMemberNo())
                .createdAt("2024-02-05")
                .build();


        List<DiaryResDto> diaryDetailResDtoList = diaryService.listDiaryByDate(diaryDateReqDto);
        log.info(diaryDetailResDtoList.toString());
    }

    @Test
    @Transactional
    void draftDiary() {

        DiarySubmitReqDto diarySubmitReqDto = DiarySubmitReqDto.builder()
                .memberId(login.getMemberNo())
                .diaryId(diaryTest.getDiaryId())
                .diaryTitle("우리애기")
                .diaryContent("내용5")
                .diaryReview("???")
                .build();
        int retValue = diaryService.submitDiary(diarySubmitReqDto);

        DiaryCreateReqDto diaryTest1 = DiaryCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .childId(childTest.getChildId())
                .build();
        diaryService.createDiary(diaryTest1);


        List<DiaryResDto> diaryDetailResDtoList = diaryService.getDraftDiary(login.getMemberNo());
        log.info(diaryDetailResDtoList.toString());
    }

    @Test
    @Transactional
    void getCalendar() {
        DiarySubmitReqDto diarySubmitReqDto = DiarySubmitReqDto.builder()
                .memberId(login.getMemberNo())
                .diaryId(diaryTest.getDiaryId())
                .diaryTitle("우리애기")
                .diaryContent("내용5")
                .diaryReview("???")
                .build();
        int retValue = diaryService.submitDiary(diarySubmitReqDto);

        DiaryCreateReqDto diaryTest1 = DiaryCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .childId(childTest.getChildId())
                .build();
        diaryService.createDiary(diaryTest1);
        diaryService.createDiary(diaryTest1);
        diaryService.createDiary(diaryTest1);
        String period = null;
        if (period == null) {
            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            period = simpleDateFormat.format(now);
        }

        List<String> periodList = diaryService.getCalendar(login.getMemberNo(), period);
        log.info(periodList.toString());
    }
}

