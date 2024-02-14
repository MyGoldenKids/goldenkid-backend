package com.ehours.goldenchild.activity.controller;

import com.ehours.goldenchild.activity.service.ActivityService;
import com.ehours.goldenchild.common.ResponseResource;
import com.ehours.goldenchild.activity.dto.RecommendActivityResDto;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;
    @GetMapping("/recommend")
    public ResponseEntity<Map<String, Object>> getRecommendStory() {
        List<RecommendActivityResDto> StoryRecommendResDtoList = activityService.getActivity();
        return ResponseResource.handleSuccess(StoryRecommendResDtoList, "추천 스토리 조회 성공");
    }
}
