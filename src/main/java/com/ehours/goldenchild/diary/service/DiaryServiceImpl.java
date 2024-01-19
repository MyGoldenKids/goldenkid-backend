package com.ehours.goldenchild.diary.service;

import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import com.ehours.goldenchild.diary.mapper.DiaryMapper;
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
}
