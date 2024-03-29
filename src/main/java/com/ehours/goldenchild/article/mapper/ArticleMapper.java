package com.ehours.goldenchild.article.mapper;

import com.ehours.goldenchild.article.dto.*;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleMapper {

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
            @Result(column = "nickname", property = "nickname")
    })
    @Select("select a.*, m.nickname from article a join member m on a.member_id = m.no where a.article_id = #{articleId} and a.article_status = 1")
    ArticleDetailDto getArticleDetailById(int articleId);

    @Update("update article set hit = hit + 1 where article_id = #{articleId}")
    int increaseArticleHit(int article);

    @InsertProvider(type = ArticleWriteProvider.class, method = "writeArticle")
    @Options(useGeneratedKeys = true, keyProperty = "articleId")
    int writeArticle(ArticleReqDto articleReqDto);

    @ResultMap("articleMap")
    @Select("select a.*, m.nickname from article a join member m on a.member_id = m.no " +
            "where a.article_status = 1 order by a.article_id desc limit #{size} offset #{page}")
    List<ArticleDetailDto> getAllArticles(Integer size, Integer page);

    @ResultMap("articleMap")
    @Select("select a.*, m.nickname from article a join member m on a.member_id = m.no " +
            "where a.article_status = 1 and a.article_title LIKE CONCAT('%', #{articleTitle}, '%') order by a.article_id desc")
    List<ArticleDetailDto> selectArticlesByTitle(String articleTitle);

    @ResultMap("articleMap")
    @Select("select a.*, m.nickname from article a join member m on a.member_id = m.no " +
            "where a.article_status = 1 and a.article_content LIKE CONCAT('%', #{articleTitle}, '%') order by a.article_id desc")
    List<ArticleDetailDto> selectArticlesByContent(String articleContent);

    @ResultMap("articleMap")
    @Select("select a.*, m.nickname from article a join member m on a.member_id = m.no " +
            "where a.article_status = 1 and m.nickname LIKE CONCAT('%', #{nickname}, '%') order by a.article_id desc")
    List<ArticleDetailDto> selectArticlesByNickname(String nickname);

    @Update("update article set article_status=0 where article_id=#{articleId}")
    int articleDeleteRequest(int articleId);

    @UpdateProvider(type = ArticleUpdateProvider.class, method = "updateArticle")
    int updateArticle(ArticleUpdateDto articleUpdateDto);


    @Select("select count(*) from recommendation where article_id = #{articleId} and member_id = #{memberId}")
    int checkRecommend(ArticleRecommendReqDto articleRecommendReqDto);

    @Delete("delete from recommendation where article_id = #{articleId} and member_id = #{memberId}")
    int deleteRecommend(ArticleRecommendReqDto articleRecommendReqDto);

    @Insert("insert into recommendation (article_id, member_id) " +
            "values(#{articleId}, #{memberId})")
    int insertRecommend(ArticleRecommendReqDto articleRecommendReqDto);

    @Select("select count(*) from recommendation where article_id = #{articleId}")
    int countRecommend(ArticleRecommendReqDto articleRecommendReqDto);

    @Select("select count(*) from article")
    int getAllArticleSize();

    @Update("update article set recommend_count = #{count} where article_id = #{articleRecommendReqDto.articleId}")
    int updateRecommendArticle(int count, ArticleRecommendReqDto articleRecommendReqDto);
}
