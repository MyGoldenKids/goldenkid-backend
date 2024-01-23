package com.ehours.goldenchild.diary.service;

import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import com.ehours.goldenchild.diary.dto.DiaryDetailResDto;
import com.ehours.goldenchild.diary.dto.DiaryResDto;
import com.ehours.goldenchild.diary.dto.DiarySubmitReqDto;
import com.ehours.goldenchild.diary.dto.DiaryUpdateReqDto;
import com.ehours.goldenchild.diary.mapper.DiaryMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryMapper diaryMapper;
    @Override
    public int createDiary(DiaryCreateReqDto diaryCreateReqDto) {
        return diaryMapper.createDiary(diaryCreateReqDto);
    }

    @Override
    public int submitDiary(DiarySubmitReqDto diarySubmitReqDto) {
        return diaryMapper.submitDiary(diarySubmitReqDto);
    }

    @Override
    public int deleteDiary(int diaryId) {
        // 1. 다이어리의 주인이 맞는지 확인

        //2. 다이어리 삭제
        return diaryMapper.deleteDiary(diaryId);
    }

    @Override
    public int updateDiary(int diaryId, DiaryUpdateReqDto diaryUpdateReqDto) {
        diaryUpdateReqDto.setDiaryId(diaryId);
        return diaryMapper.updateDiary(diaryUpdateReqDto);
    }

    @Override
    public List<DiaryResDto> listDiary(int memberId) {
        return diaryMapper.listDiary(memberId);
    }

    @Override
    public DiaryDetailResDto detailDiary(int diaryId) {
        return diaryMapper.detailDiary(diaryId);
    }

}
