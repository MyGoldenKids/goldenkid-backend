package com.ehours.goldenchild.child.mapper;

import com.ehours.goldenchild.child.dto.ChildDetailResDto;
import com.ehours.goldenchild.child.dto.ChildModifyReqDto;
import com.ehours.goldenchild.child.dto.ChildRegisterReqDto;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ChildMapper {
    @Insert("INSERT INTO child (child_name, child_birth, child_gender, member_id)"
            + "values (#{childName}, #{childBirth}, #{childGender}, #{memberId})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "childId")
    int registerChild(ChildRegisterReqDto childRegisterReqDto);

    @Select("SELECT child_id, child_name, date_format(child_birth, '%Y-%m-%d'), child_gender, file_id from child where child_id = #{childId}")
    ChildDetailResDto detailChild(int childId);

    @Update("UPDATE child set child_name = #{childName}, child_birth = #{childBirth}, child_gender = #{childGender}, file_id = #{fileId} "
    + "where child_id = #{childId}"
    )
    int modifyChild(ChildModifyReqDto childModifyReqDto);

    @Delete("DELETE from child where child_id = #{childId}")
    int deleteChild(int childId);

    @Select("SELECT child_id, child_name, date_format(child_birth, '%Y-%m-%d'), child_gender, file_id from child where member_id = #{memberId}")
    List<ChildDetailResDto> getMyChild(int memberId);
}
