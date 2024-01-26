package com.ehours.goldenchild.member.mapper;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberMapper {
    @Insert("insert into member (member_id, password, nickname, phone_number)"
            + "values (#{memberId}, #{password}, #{nickname}, #{phoneNumber})")
    int signup(MemberSignUpReqDto memberSignUpReqDto);

    @Select("SELECT no, nickname from member "
            + "WHERE member_id = #{memberId}"
    )
    MemberLoginResDto login(MemberLoginReqDto memberLoginReqDto);

    // 해싱된 패스워드를 반환해 비교하기 위한 sql문
    @Select("SELECT password from member "
            + "WHERE member_id = #{memberId}"
    )
    String pwdCheck(MemberLoginReqDto memberLoginReqDto);

    @Select("SELECT count(member_id) from member where member_id = #{memberId}")
    int idCheck(String memberId);

    @Select("SELECT member_id, nickname, phone_number from member where member_id = #{memberId}")
    MemberDetailResDto memberDetail(String memberId);

    @Update("UPDATE member set password = #{password}, nickname = #{nickname}, phone_number = #{phoneNumber} where member_id = #{memberId}")
    int memberModify(MemberModifyReqDto memberModifyReqDto);

    @Update("UPDATE member set member_status = 0  where member_id = #{memberId}")
    int memberSignOut(String memberId);
}
