package com.ehours.goldenchild.article.service;

import com.ehours.goldenchild.article.dto.ArticleDetailDto;
import com.ehours.goldenchild.article.dto.ArticleDto;
import com.ehours.goldenchild.article.dto.ArticleReqDto;
import com.ehours.goldenchild.article.dto.ArticleUpdateDto;
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
}
