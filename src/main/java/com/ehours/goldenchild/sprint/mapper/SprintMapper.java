package com.ehours.goldenchild.sprint.mapper;

import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import com.ehours.goldenchild.sprint.dto.SprintResponseDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SprintMapper {

    @Select("SELECT sprint_id, sprint_title, sprint_status, created_at, start_date, end_date from sprint where member_id = #{memberId}")
    List<SprintResponseDto> getSprintList(int memberId);


    @Insert("INSERT into sprint (member_id, sprint_title, start_date, end_date) " +
            "values(#{memberId}, #{sprintTitle}, #{startDate}, #{endDate})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "sprintId")
    int createSprint(SprintCreateReqDto sprintCreateReqDto);
}
