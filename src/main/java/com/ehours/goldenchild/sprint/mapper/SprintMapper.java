package com.ehours.goldenchild.sprint.mapper;

import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SprintMapper {
    @Insert("INSERT into sprint (member_id, sprint_title, start_date, end_date) " +
            "values(#{memberId}, #{sprintTitle}, #{startDate}, #{endDate})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "sprintId")
    int createSprint(SprintCreateReqDto sprintCreateReqDto);
}
