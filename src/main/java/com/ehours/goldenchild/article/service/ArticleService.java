package com.ehours.goldenchild.article.service;

import com.ehours.goldenchild.article.dto.ArticleDetailDto;
import com.ehours.goldenchild.article.dto.ArticleDto;
import com.ehours.goldenchild.article.dto.ArticleReqDto;
import java.util.List;

public interface ArticleService {
    int writeArticle(ArticleReqDto articleReqDto);
    List<ArticleDetailDto> getAllArticles();
    int articleDeleteRequest(int articleId);
    ArticleDetailDto getArticleDetailById(int articleId);
}
