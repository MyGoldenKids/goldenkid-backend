package com.ehours.goldenchild.sprint.controller;

import com.ehours.goldenchild.common.ResponseResource;
import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import com.ehours.goldenchild.sprint.dto.SprintModifyReqDto;
import com.ehours.goldenchild.sprint.dto.SprintResponseDto;
import com.ehours.goldenchild.sprint.dto.SprintStatusReqDto;
import com.ehours.goldenchild.sprint.service.SprintService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        if (retValue == 1) return ResponseResource.handleSuccess(sprintCreateReqDto.getSprintId(), "생성 성공");
        else return ResponseResource.handleError("생성 실패");
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<Map<String, Object>> getSprintList(@PathVariable int memberId) {
        List<SprintResponseDto> sprintResponseDtoList = sprintService.getSprintList(memberId);
        return ResponseResource.handleSuccess(sprintResponseDtoList, "조회 성공");
    }

    @PatchMapping("/status/{sprintId}")
    public ResponseEntity<Map<String, Object>> updateSprintStatus(@PathVariable int sprintId, @RequestBody SprintStatusReqDto sprintStatusReqDto) {
        int retValue = sprintService.updateSprintStatus(sprintId, sprintStatusReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(sprintId, "상태 수정 성공");
        else return ResponseResource.handleError("상태 수정 실패");
    }

    @PutMapping("/modify/{sprintId}")
    public ResponseEntity<Map<String, Object>> updateSprint(@PathVariable int sprintId, @RequestBody SprintModifyReqDto sprintModifyReqDto) {
        int retValue = sprintService.updateSprint(sprintId, sprintModifyReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(sprintId, "스프린트 수정 성공");
        else return ResponseResource.handleError("스프린트 수정 실패");
    }

    @DeleteMapping("/delete/{sprintId}/member/{memberId}")
    public ResponseEntity<Map<String, Object>> deleteSprint(@PathVariable int sprintId, @PathVariable int memberId) {
        int retValue = sprintService.deleteSprint(sprintId, memberId);
        if (retValue == 1) return ResponseResource.handleSuccess(sprintId, "스프린트 삭제 성공");
        else return ResponseResource.handleError("스프린트 삭제 실패");
    }
}
