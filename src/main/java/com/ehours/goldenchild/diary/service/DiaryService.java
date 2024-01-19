package com.ehours.goldenchild.diary.service;

import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import com.ehours.goldenchild.diary.dto.DiarySubmitReqDto;

public interface DiaryService {

    int createDiary(DiaryCreateReqDto diaryCreateReqDto);
    int submitDiary(DiarySubmitReqDto diarySubmitReqDto);
}
