package com.ehours.goldenchild.sprint.mapper;

import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import com.ehours.goldenchild.sprint.dto.SprintResponseDto;
import com.ehours.goldenchild.sprint.dto.SprintStatusReqDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Mapper
public interface SprintMapper {

    @Select("SELECT sprint_id, sprint_title, sprint_status, created_at, start_date, end_date from sprint where member_id = #{memberId}")
    List<SprintResponseDto> getSprintList(int memberId);

    @Insert("INSERT into sprint (member_id, sprint_title, start_date, end_date) " +
            "values(#{memberId}, #{sprintTitle}, #{startDate}, #{endDate})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "sprintId")
    int createSprint(SprintCreateReqDto sprintCreateReqDto);

    @Update("UPDATE sprint set sprint_status = #{sprintStatusReqDto.sprintStatus} where sprint_id = #{sprintId} and member_id = #{sprintStatusReqDto.memberId}")
    int updateSprintStatus(@Param("sprintId") int sprintId, SprintStatusReqDto sprintStatusReqDto);

}
