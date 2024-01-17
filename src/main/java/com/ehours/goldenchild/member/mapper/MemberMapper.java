package com.ehours.goldenchild.member.mapper;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
    @Insert("insert into member (member_id, password, nickname, phone_number)"
            + "values (#{memberId}, #{password}, #{nickname}, #{phoneNumber})")
    int signup(MemberSignUpReqDto memberSignUpReqDto);

    @Select("SELECT member_id from member where member_id = #{memberId}")
    String idCheck(String memberId);

    @Select("SELECT member_id, nickname, phone_number from member where member_id = #{memberId}")
    MemberDetailResDto memberDetail(String memberId);
}
