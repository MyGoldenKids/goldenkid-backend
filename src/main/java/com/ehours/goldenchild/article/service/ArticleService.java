package com.ehours.goldenchild.article.service;

import com.ehours.goldenchild.article.dto.*;

import java.util.List;

public interface ArticleService {
    int writeArticle(ArticleReqDto articleReqDto);
    List<ArticleDetailDto> getAllArticles(Integer size, Integer page);
    List<ArticleDetailDto> selectArticlesByTitle(String articleTitle);
    List<ArticleDetailDto> selectArticlesByContent(String articleContent);
    List<ArticleDetailDto> selectArticlesByNickname(String nickname);
    int articleDeleteRequest(int articleId);
    ArticleDetailDto getArticleDetailById(int articleId);
    int updateArticle(ArticleUpdateDto articleUpdateDto);
    int recommendArticle(ArticleRecommendReqDto articleRecommendReqDto);
    int checkRecommendArticle(int articleId, int memberId);
}
