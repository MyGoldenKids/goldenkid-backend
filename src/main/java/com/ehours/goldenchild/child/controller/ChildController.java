package com.ehours.goldenchild.child.controller;

import com.ehours.goldenchild.child.dto.ChildDetailResDto;
import com.ehours.goldenchild.child.dto.ChildModifyReqDto;
import com.ehours.goldenchild.child.dto.ChildRegisterReqDto;
import com.ehours.goldenchild.child.service.ChildService;
import com.ehours.goldenchild.common.ResponseResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/child")
@RequiredArgsConstructor
public class ChildController {
    private final ChildService childService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerChild(ChildRegisterReqDto childRegisterReqDto) {
        int retValue = childService.registerChild(childRegisterReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "아이 등록 성공");
        else return ResponseResource.handleError("아이 등록 실패");
    }

    @GetMapping("/detail")
    public ResponseEntity<Map<String, Object>> detailChild(int childId) {
        ChildDetailResDto retValue = childService.detailChild(childId);
        if (retValue == null) return ResponseResource.handleError("아이 등록 실패");
        else return ResponseResource.handleSuccess(retValue, "아이 조회 성공");
    }

    @PutMapping("/modify")
    public ResponseEntity<Map<String, Object>> modifyChild(ChildModifyReqDto childModifyReqDto) {
        int retValue = childService.modifyChild(childModifyReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "수정 성공");
        else return ResponseResource.handleError("수정 실패");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteChild(int childId) {
        int retValue = childService.deleteChild(childId);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "삭제 성공");
        else return ResponseResource.handleError("삭제 실패");
    }
}
