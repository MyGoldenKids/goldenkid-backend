package com.ehours.goldenchild.child.controller;

import com.ehours.goldenchild.child.dto.ChildRegisterReqDto;
import com.ehours.goldenchild.child.service.ChildService;
import com.ehours.goldenchild.common.ResponseResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
