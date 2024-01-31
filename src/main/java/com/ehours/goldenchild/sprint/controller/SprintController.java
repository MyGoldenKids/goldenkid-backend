package com.ehours.goldenchild.sprint.controller;

import com.ehours.goldenchild.common.ResponseResource;
import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import com.ehours.goldenchild.sprint.service.SprintService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jira/sprint")
@RequiredArgsConstructor
public class SprintController {
    private final SprintService sprintService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createSprint(@RequestBody SprintCreateReqDto sprintCreateReqDto) {
        int retValue = sprintService.createSprint(sprintCreateReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "생성 성공");
        else return ResponseResource.handleError("생성 실패");
    }
}
