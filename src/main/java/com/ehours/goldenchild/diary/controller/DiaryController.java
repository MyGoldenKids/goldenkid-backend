package com.ehours.goldenchild.diary.controller;

import com.ehours.goldenchild.common.ResponseResource;
import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import com.ehours.goldenchild.diary.service.DiaryService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createDiary(DiaryCreateReqDto diaryCreateReqDto) {
        int retValue = diaryService.createDiary(diaryCreateReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "일기 등록 성공");
        else return ResponseResource.handleError("일기 등록 실패");
    }


}
