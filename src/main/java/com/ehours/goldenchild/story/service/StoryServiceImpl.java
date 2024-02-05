package com.ehours.goldenchild.story.service;

import com.ehours.goldenchild.story.dto.StoryRequestDto;
import com.ehours.goldenchild.story.dto.StoryDetailResDto;
import com.ehours.goldenchild.story.dto.StoryStatusReqDto;
import com.ehours.goldenchild.story.mapper.StoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService{
    private final StoryMapper storyMapper;

    @Override
    public int createStory(StoryRequestDto storyRequestDto) {
        return storyMapper.createStory(storyRequestDto);
    }

    @Override
    public int updateStoryStatus(int storyId, StoryStatusReqDto storyStatusReqDto) {
        return storyMapper.updateStoryStatus(storyId, storyStatusReqDto);
    }

    @Override
    public int updateStory(int storyId, StoryRequestDto storyRequestDto) {
        return storyMapper.updateStory(storyId, storyRequestDto);
    }

    @Override
    public StoryDetailResDto getStoryById(int storyId, int memberId) {
        return storyMapper.getStoryById(storyId, memberId);
    }

    @Override
    public List<StoryDetailResDto> getStoryBySprintId(int sprintId, int memberId) {
        return storyMapper.getStoryBySprintId(sprintId, memberId);
    }

    @Override
    public int deleteStory(int storyId, int memberId) {
        return storyMapper.deleteStory(storyId, memberId);
    }
}
