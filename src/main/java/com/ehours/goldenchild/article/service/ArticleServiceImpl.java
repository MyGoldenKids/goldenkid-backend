package com.ehours.goldenchild.article.service;

import com.ehours.goldenchild.article.dto.ArticleDetailDto;
import com.ehours.goldenchild.article.dto.ArticleDto;
import com.ehours.goldenchild.article.dto.ArticleReqDto;
import com.ehours.goldenchild.article.mapper.ArticleMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{
    private final ArticleMapper articleMapper;

    @Override
    public int writeArticle(ArticleReqDto articleReqDto) {
        return articleMapper.writeArticle(articleReqDto);
    }

    @Override
    public List<ArticleDetailDto> getAllArticles() {
        return articleMapper.getAllArticles();
    }

    @Override
    public int articleDeleteRequest(int articleId) {
        return articleMapper.articleDeleteRequest(articleId);
    }

    @Override
    public ArticleDetailDto getArticleDetailById(int articleId) {
        return articleMapper.getArticleDetailById(articleId);
    }
}
