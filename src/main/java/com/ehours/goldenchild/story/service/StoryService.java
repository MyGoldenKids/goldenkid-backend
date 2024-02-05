package com.ehours.goldenchild.story.service;

import com.ehours.goldenchild.story.dto.StoryRequestDto;
import com.ehours.goldenchild.story.dto.StoryDetailResDto;
import com.ehours.goldenchild.story.dto.StoryStatusReqDto;

import java.util.List;

public interface StoryService {
    int createStory(StoryRequestDto storyRequestDto);
    int updateStoryStatus(int storyId, StoryStatusReqDto storyStatusReqDto);
    int updateStory(int storyId, StoryRequestDto storyRequestDto);
    StoryDetailResDto getStoryById(int storyId, int memberId);
    List<StoryDetailResDto> getStoryBySprintId(int sprintId, int memberId);
    int deleteStory(int storyId, int memberId);
}
