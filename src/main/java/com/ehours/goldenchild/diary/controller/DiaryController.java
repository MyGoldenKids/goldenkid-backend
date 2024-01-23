package com.ehours.goldenchild.diary.controller;

import com.ehours.goldenchild.common.ResponseResource;
import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import com.ehours.goldenchild.diary.dto.DiaryDetailResDto;
import com.ehours.goldenchild.diary.dto.DiaryResDto;
import com.ehours.goldenchild.diary.dto.DiarySubmitReqDto;
import com.ehours.goldenchild.diary.dto.DiaryUpdateReqDto;
import com.ehours.goldenchild.diary.service.DiaryService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<Map<String, Object>> deleteDiary(@PathVariable int diaryId) {
        int retValue = diaryService.deleteDiary(diaryId);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "삭제 성공");
        else return ResponseResource.handleError("삭제 실패");
    }

    @PutMapping("/update/{diaryId}")
    public ResponseEntity<Map<String, Object>> updateDiary(@PathVariable int diaryId, DiaryUpdateReqDto diaryUpdateReqDto) {
        int retValue = diaryService.updateDiary(diaryId, diaryUpdateReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "수정 성공");
        else return ResponseResource.handleError("수정 실패");
    }
    @GetMapping("/list/{memberId}")
    public ResponseEntity<Map<String, Object>> listDiary(@PathVariable int memberId) {
        List<DiaryResDto> diaryListResDtos = diaryService.listDiary(memberId);
        if (diaryListResDtos != null) return ResponseResource.handleSuccess(diaryListResDtos, "조회 성공");
        else return ResponseResource.handleError("조회 실패");
    }

    @GetMapping("/detail/{diaryId}")
    public ResponseEntity<Map<String, Object>> detailDiary(@PathVariable int diaryId) {
        DiaryDetailResDto diaryDetailResDto = diaryService.detailDiary(diaryId);
        if (diaryDetailResDto != null) return ResponseResource.handleSuccess(diaryDetailResDto, "상세정보 조회 성공");
        else return ResponseResource.handleError("상세정보 조회 실패");
    }


}
