package com.ehours.goldenchild.story.controller;

import com.ehours.goldenchild.common.ResponseResource;
import com.ehours.goldenchild.story.dto.StoryCreateReqDto;
import com.ehours.goldenchild.story.dto.StoryStatusReqDto;
import com.ehours.goldenchild.story.service.StoryService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}