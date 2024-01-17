package com.ehours.goldenchild.article.mapper;

import com.ehours.goldenchild.article.dto.ArticleDto;
import com.ehours.goldenchild.article.dto.ArticleReqDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article (member_id, file_list_id, article_title, article_content)"
    + "values(#{memberId},#{fileListId},#{articleTitle},#{articleContent})")
    int writeArticle(ArticleReqDto articleReqDto);

    @Select("select * from article")
    @Results(id = "articleMap", value = {
            @Result(column = "article_id", property = "articleId"),
            @Result(column = "member_id", property = "memberId"),
            @Result(column = "file_list_id", property = "fileListId"),
            @Result(column = "article_title", property = "articleTitle"),
            @Result(column = "article_content", property = "articleContent"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "modified_at", property = "modifiedAt"),
            @Result(column = "hit", property = "hit"),
            @Result(column = "recommend_count", property = "recommendCount"),
            @Result(column = "article_status", property = "articleStatus")
    })
    List<ArticleDto> getAllArticles();

    @Update("update article set article_status=0 where article_id=#{articleId}")
    int articleDeleteRequest(int articleId);
}
