package com.ehours.goldenchild.child.mapper;

import com.ehours.goldenchild.child.dto.ChildDetailResDto;
import com.ehours.goldenchild.child.dto.ChildRegisterReqDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChildMapper {
    @Insert("INSERT INTO child (child_name, child_birth, child_gender, member_id)"
            + "values (#{childName}, #{childBirth}, #{childGender}, #{memberId})"
    )
    int registerChild(ChildRegisterReqDto childRegisterReqDto);

    @Select("SELECT child_name, child_birth, child_gender, file_id from child where child_id = #{childId}")
    ChildDetailResDto detailChild(int childId);
}
