package com.ehours.goldenchild.story.mapper;

import com.ehours.goldenchild.story.dto.StoryRequestDto;
import com.ehours.goldenchild.story.dto.StoryDetailResDto;
import com.ehours.goldenchild.story.dto.StoryStatusReqDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StoryMapper {

    @Insert("INSERT into story (member_id, story_content, story_point, sprint_id) " +
            "values (#{memberId}, #{storyContent}, #{storyPoint}, #{sprintId})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "storyId")
    int createStory(StoryRequestDto storyRequestDto);

    @Update("UPDATE story set story_status = #{storyStatusReqDto.storyStatus} " +
            "where story_id = #{storyId} and member_id = #{storyStatusReqDto.memberId}")
    int updateStoryStatus(int storyId, StoryStatusReqDto storyStatusReqDto);

    @Update("UPDATE story set story_content = #{storyRequestDto.storyContent}, story_point = #{storyRequestDto.storyPoint}, sprint_id = #{storyRequestDto.sprintId} " +
            "where story_id = #{storyId} and member_id = #{storyRequestDto.memberId}")
    int updateStory(int storyId, StoryRequestDto storyRequestDto);

    @Results(id = "storyMap", value = {
            @Result(column = "story_id", property = "storyId"),
            @Result(column = "member_id", property = "memberId"),
            @Result(column = "sprint_id", property = "sprintId"),
            @Result(column = "story_content", property = "storyContent"),
            @Result(column = "story_point", property = "storyPoint"),
            @Result(column = "story_status", property = "storyStatus"),
            @Result(column = "created_at", property = "createdAt"),
    })
    @Select("SELECT * from story where story_id = #{storyId} and member_id = #{memberId}")
    StoryDetailResDto getStoryById(int storyId, int memberId);

    @ResultMap("storyMap")
    @Select("SELECT * from story where sprint_id = #{sprintId} and member_id = #{memberId}")
    List<StoryDetailResDto> getStoryBySprintId(int sprintId, int memberId);

    @Delete("DELETE from story where story_id = #{storyId} and member_id = #{memberId}")
    int deleteStory(int storyId, int memberId);
}
