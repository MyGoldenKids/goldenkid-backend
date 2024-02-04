package com.ehours.goldenchild.comment.mapper;

import com.ehours.goldenchild.article.dto.ArticleDetailDto;
import com.ehours.goldenchild.comment.dto.CommentDetailResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
    @Select("select c.*, m.nickname from comment c join member m on c.member_id = m.no")
    List<CommentDetailResDto> getCommentByArticleId(int articleId);
}
