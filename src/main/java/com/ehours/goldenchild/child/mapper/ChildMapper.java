package com.ehours.goldenchild.child.mapper;

import com.ehours.goldenchild.child.dto.ChildRegisterReqDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChildMapper {
    @Insert("INSERT INTO child (child_name, child_birth, child_gender, member_id)"
            + "values (#{childName}, #{childBirth}, #{childGender}, #{memberId})"
    )
    int registerChild(ChildRegisterReqDto childRegisterReqDto);
}
