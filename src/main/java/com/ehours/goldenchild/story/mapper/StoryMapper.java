package com.ehours.goldenchild.story.mapper;

import com.ehours.goldenchild.story.dto.StoryCreateReqDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StoryMapper {

    @Insert("INSERT into story (member_id, story_content, story_point, sprint_id) " +
            "values (#{memberId}, #{storyContent}, #{storyPoint}, #{sprintId})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "storyId")
    int createStory(StoryCreateReqDto storyCreateReqDto);
}
