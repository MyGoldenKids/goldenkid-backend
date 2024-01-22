package com.ehours.goldenchild.diary.controller;

import com.ehours.goldenchild.common.ResponseResource;
import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import com.ehours.goldenchild.diary.dto.DiarySubmitReqDto;
import com.ehours.goldenchild.diary.service.DiaryService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PutMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitDiary(DiarySubmitReqDto diarySubmitReqDto) {
        int retValue = diaryService.submitDiary(diarySubmitReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "일기 제출 성공");
        else return ResponseResource.handleError("일기 제출 실패");
    }

    @DeleteMapping("/delete/{diaryId}")
    public ResponseEntity<Map<String, Object>> deleteDiary(@PathVariable int diaryId, int memberId) {
        int retValue = diaryService.deleteDiary(diaryId);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "삭제 성공");
        else return ResponseResource.handleError("삭제 실패");
    }


}
