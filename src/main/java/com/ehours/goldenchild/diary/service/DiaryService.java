package com.ehours.goldenchild.diary.service;

import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import com.ehours.goldenchild.diary.dto.DiaryDateReqDto;
import com.ehours.goldenchild.diary.dto.DiaryDetailResDto;
import com.ehours.goldenchild.diary.dto.DiaryResDto;
import com.ehours.goldenchild.diary.dto.DiarySubmitReqDto;
import com.ehours.goldenchild.diary.dto.DiaryUpdateReqDto;
import java.util.List;

public interface DiaryService {

    int createDiary(DiaryCreateReqDto diaryCreateReqDto);
    int submitDiary(DiarySubmitReqDto diarySubmitReqDto);
    int deleteDiary(int diaryId);

    int updateDiary(DiaryUpdateReqDto diaryUpdateReqDto);
    List<DiaryResDto> listDiary(int memberId);
    DiaryDetailResDto detailDiary(int diaryId);
    List<DiaryDetailResDto> listDiaryByDate(DiaryDateReqDto diaryDateReqDto);
}
