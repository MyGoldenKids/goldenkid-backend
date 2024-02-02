package com.ehours.goldenchild.story.mapper;

import com.ehours.goldenchild.story.dto.StoryCreateReqDto;
import com.ehours.goldenchild.story.dto.StoryStatusReqDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StoryMapper {

    @Insert("INSERT into story (member_id, story_content, story_point, sprint_id) " +
            "values (#{memberId}, #{storyContent}, #{storyPoint}, #{sprintId})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "storyId")
    int createStory(StoryCreateReqDto storyCreateReqDto);

    @Update("UPDATE story set story_status = #{storyStatusReqDto.storyStatus} " +
            "where story_id = #{storyId} and member_id = #{storyStatusReqDto.memberId}")
    int updateStoryStatus(@Param("storyId") int storyId, StoryStatusReqDto storyStatusReqDto);
}
