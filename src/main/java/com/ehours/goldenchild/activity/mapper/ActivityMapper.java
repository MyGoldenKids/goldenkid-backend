package com.ehours.goldenchild.activity.mapper;

import com.ehours.goldenchild.activity.dto.RecommendActivityResDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ActivityMapper {
    @Select("SELECT activity_description from activity_list ORDER BY RAND() LIMIT 10")
    List<RecommendActivityResDto> recommendActivity();
}
