package com.ehours.goldenchild.diary;

import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import com.ehours.goldenchild.diary.dto.DiarySubmitReqDto;
import com.ehours.goldenchild.diary.dto.DiaryUpdateReqDto;
import com.ehours.goldenchild.diary.service.DiaryService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class DiaryTests {

    @Autowired
    DiaryService diaryService;

    @Test
    @Transactional
    void createDiary() {
        DiaryCreateReqDto diaryCreateReqDto = DiaryCreateReqDto.builder()
                .memberId(3)
                .childId(1)
                .build();
        int retValue = diaryService.createDiary(diaryCreateReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(diaryCreateReqDto.toString());
    }

    @Test
    @Transactional
    void submitDiary() {
        DiarySubmitReqDto diarySubmitReqDto = DiarySubmitReqDto.builder()
                .diaryId(5)
                .diaryTitle("??")
                .diaryContent("내용")
                .diaryReview("???")
                .build();
        int retValue = diaryService.submitDiary(diarySubmitReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(diarySubmitReqDto.toString());
    }

    @Test
    @Transactional
    void deleteDiary() {
        int retValue = diaryService.deleteDiary(0);
        Assertions.assertThat(retValue).isEqualTo(1);
    }

    @Test
    @Transactional
    void updateDiary() {
        DiaryUpdateReqDto diaryUpdateReqDto = DiaryUpdateReqDto.builder()
                .diaryTitle("우리 아이")
                .diaryContent("이게 맞나여?")
                .diaryReview("")
                .memberId(3)
                .childId(1)
                .build();
        log.info(diaryUpdateReqDto.toString());
        int retValue = diaryService.updateDiary(4, diaryUpdateReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(diaryUpdateReqDto.toString());
    }
}

