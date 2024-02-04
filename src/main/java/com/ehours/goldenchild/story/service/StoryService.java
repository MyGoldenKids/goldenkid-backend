package com.ehours.goldenchild.story.service;

import com.ehours.goldenchild.story.dto.StoryCreateReqDto;
import com.ehours.goldenchild.story.dto.StoryDetailResDto;
import com.ehours.goldenchild.story.dto.StoryStatusReqDto;

import java.util.List;

public interface StoryService {
    int createStory(StoryCreateReqDto storyCreateReqDto);
    int updateStoryStatus(int storyId, StoryStatusReqDto storyStatusReqDto);
    StoryDetailResDto getStoryById(int storyId, int memberId);
    List<StoryDetailResDto> getStoryBySprintId(int sprintId, int memberId);
}
