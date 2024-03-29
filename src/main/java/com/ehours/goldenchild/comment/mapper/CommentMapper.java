package com.ehours.goldenchild.comment.mapper;

import com.ehours.goldenchild.comment.dto.CommentDetailResDto;
import com.ehours.goldenchild.comment.dto.CommentRequestDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Results(id = "commentMap", value = {
            @Result(column = "comment_id", property = "commentId"),
            @Result(column = "article_id", property = "articleId"),
            @Result(column = "member_id", property = "memberId"),
            @Result(column = "nickname", property = "nickname"),
            @Result(column = "content", property = "content"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "modified_at", property = "modifiedAt")
    })
    @Select("select c.*, m.nickname from comment c join member m on c.member_id = m.no where article_id = #{articleId}")
    List<CommentDetailResDto> getCommentByArticleId(int articleId);

    @Insert("INSERT INTO comment(article_id, member_id, content) " +
            "values(#{articleId}, #{commentRequestDto.memberId}, #{commentRequestDto.content})")
    @Options(useGeneratedKeys = true, keyProperty = "commentRequestDto.commentId")
    int writeComment(int articleId, CommentRequestDto commentRequestDto);

    @Update("UPDATE comment set content = #{commentRequestDto.content}, modified_at = CURRENT_TIMESTAMP " +
            "where comment_id = #{commentId} and member_id = #{commentRequestDto.memberId}")
    int updateComment(int commentId, CommentRequestDto commentRequestDto);

    @Delete("DELETE from comment where comment_id = #{commentId} and member_id = #{memberId}")
    int deleteComment(int commentId, int memberId);
}
