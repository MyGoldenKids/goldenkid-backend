package com.ehours.goldenchild.diary.mapper;

import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiaryMapper {
    @Insert("INSERT INTO diary (member_id, child_id, diary_title, diary_content, diary_review)"
    + "values(#{memberId}, #{childId}, '', '', '')"
    )
    int createDiary(DiaryCreateReqDto diaryCreateReqDto);
}
