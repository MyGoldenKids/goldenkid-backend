package com.ehours.goldenchild.story.controller;

import com.ehours.goldenchild.common.ResponseResource;
import com.ehours.goldenchild.story.dto.StoryCreateReqDto;
import com.ehours.goldenchild.story.dto.StoryDetailResDto;
import com.ehours.goldenchild.story.dto.StoryStatusReqDto;
import com.ehours.goldenchild.story.service.StoryService;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jira/story")
@RequiredArgsConstructor
@Slf4j
public class StoryController {
    private final StoryService storyService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createStory(@RequestBody StoryCreateReqDto storyCreateReqDto) {
        int retValue = storyService.createStory(storyCreateReqDto);
        if(retValue == 1) return ResponseResource.handleSuccess(storyCreateReqDto.getStoryId(),"스토리 생성 성공");
        else return ResponseResource.handleError("스토리 생성 실패");
    }

    @PatchMapping("/modify/{storyId}")
    public ResponseEntity<Map<String, Object>> updateStoryStatus(@PathVariable int storyId, StoryStatusReqDto storyStatusReqDto) {
        int retValue = storyService.updateStoryStatus(storyId, storyStatusReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "상태 수정 성공");
        else return ResponseResource.handleError("상태 수정 실패");
    }

    @GetMapping("/detail/{storyId}/member/{memberId}")
    public ResponseEntity<Map<String, Object>> getStoryById(@PathVariable int storyId, @PathVariable int memberId) {
        StoryDetailResDto storyDetailResDto = storyService.getStoryById(storyId, memberId);
        if (storyDetailResDto != null) return ResponseResource.handleSuccess(storyDetailResDto, "조회 성공");
        else return ResponseResource.handleError("조회 실패");
    }

    @GetMapping("/list/{sprintId}/member/{memberId}")
    public ResponseEntity<Map<String, Object>> getStoryBySprintId(@PathVariable int sprintId, @PathVariable int memberId) {
        List<StoryDetailResDto> storyDetailResDtoList = storyService.getStoryBySprintId(sprintId, memberId);
        return ResponseResource.handleSuccess(storyDetailResDtoList, "조회 성공");
    }

    @DeleteMapping("/delete/{storyId}/member/{memberid}")
    public ResponseEntity<Map<String, Object>> deleteStory(@PathVariable int storyId, @PathVariable int memberId) {
        int retValue = storyService.deleteStory(storyId, memberId);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "스토리 삭제 성공");
        else return ResponseResource.handleError("스토리 삭제 실패");
    }
}
