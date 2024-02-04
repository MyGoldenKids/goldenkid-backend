package com.ehours.goldenchild.story.service;

import com.ehours.goldenchild.story.dto.StoryCreateReqDto;
import com.ehours.goldenchild.story.dto.StoryDetailResDto;
import com.ehours.goldenchild.story.dto.StoryStatusReqDto;

public interface StoryService {
    int createStory(StoryCreateReqDto storyCreateReqDto);
    int updateStoryStatus(int storyId, StoryStatusReqDto storyStatusReqDto);
    StoryDetailResDto getStoryById(int storyId, int memberId);
}
