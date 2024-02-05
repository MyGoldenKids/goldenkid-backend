package com.ehours.goldenchild.jwt.mapper;

import com.ehours.goldenchild.jwt.dto.RefreshTokenDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JwtMapper {
    @Insert("INSERT INTO refresh_token (token_id, refresh_token)"
            + "values(#{tokenId}, #{refreshToken})")
    int createRefreshToken(RefreshTokenDto refreshTokenDto);

    @Select("SELECT refresh_token from refresh_token " +
            "where token_id = #{tokenId}")
    String validateRefreshToken(String tokenId);

    @Delete("DELETE FROM refresh_token " +
            "where token_id = #{tokenId}")
    int deleteToken(String tokenId);
}
