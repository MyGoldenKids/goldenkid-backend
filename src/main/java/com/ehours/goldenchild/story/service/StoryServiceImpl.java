package com.ehours.goldenchild.story.service;

import com.ehours.goldenchild.story.dto.StoryCreateReqDto;
import com.ehours.goldenchild.story.mapper.StoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService{
    private final StoryMapper storyMapper;


    @Override
    public int createStory(StoryCreateReqDto storyCreateReqDto) {
        return storyMapper.createStory(storyCreateReqDto);
    }
}
