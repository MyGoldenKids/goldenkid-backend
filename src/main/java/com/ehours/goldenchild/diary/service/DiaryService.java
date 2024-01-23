package com.ehours.goldenchild.diary.service;

import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import com.ehours.goldenchild.diary.dto.DiaryListResDto;
import com.ehours.goldenchild.diary.dto.DiarySubmitReqDto;
import com.ehours.goldenchild.diary.dto.DiaryUpdateReqDto;
import java.util.List;

public interface DiaryService {

    int createDiary(DiaryCreateReqDto diaryCreateReqDto);
    int submitDiary(DiarySubmitReqDto diarySubmitReqDto);
    int deleteDiary(int diaryId);

    int updateDiary(int diaryId, DiaryUpdateReqDto diaryUpdateReqDto);
    List<DiaryListResDto> listDiary(int memberId);
}
