package com.ehours.goldenchild.diary.mapper;

import com.ehours.goldenchild.diary.dto.DiaryCreateReqDto;
import com.ehours.goldenchild.diary.dto.DiaryListResDto;
import com.ehours.goldenchild.diary.dto.DiarySubmitReqDto;
import com.ehours.goldenchild.diary.dto.DiaryUpdateReqDto;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DiaryMapper {
    @Insert("INSERT INTO diary (member_id, child_id, diary_title, diary_content, diary_review)"
    + "values(#{memberId}, #{childId}, '', '', '')"
    )
    int createDiary(DiaryCreateReqDto diaryCreateReqDto);

    @Update("UPDATE diary SET diary_title = #{diaryTitle}, diary_content = #{diaryContent}, "
            + "diary_review = #{diaryReview}, diary_status = 1 "
            + "where diary_id = #{diaryId}"
    )
    int submitDiary(DiarySubmitReqDto diarySubmitReqDto);

    @Delete("DELETE from diary where diary_id = #{diaryId}")
    int deleteDiary(int diaryId);

    @Update("UPDATE diary SET diary_title = #{diaryTitle}, diary_content = #{diaryContent}, "
            + "diary_review = #{diaryReview}, file_list_id = #{fileListId} "
            + "where diary_id = #{diaryId} and member_id = #{memberId}"
    )
    int updateDiary(DiaryUpdateReqDto diaryUpdateReqDto);

    @Select("SELECT diary_id, diary_title, created_at from diary "
            + "where member_id = #{memberId} and diary_status = 1"
    )
    List<DiaryListResDto> listDiary(int memberId);
}
