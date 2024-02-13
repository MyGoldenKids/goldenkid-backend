package com.ehours.goldenchild.article.service;

import com.ehours.goldenchild.article.dto.*;
import com.ehours.goldenchild.article.mapper.ArticleMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{
    private final ArticleMapper articleMapper;

    @Override
    public int writeArticle(ArticleReqDto articleReqDto) {
        return articleMapper.writeArticle(articleReqDto);
    }

    @Override
    public List<ArticleDetailDto> getAllArticles(Integer size, Integer page) {
        return articleMapper.getAllArticles(size, page);
    }

    @Override
    public List<ArticleDetailDto> selectArticlesByTitle(String articleTitle) {
        return articleMapper.selectArticlesByTitle(articleTitle);
    }

    @Override
    public List<ArticleDetailDto> selectArticlesByContent(String articleContent) {
        return articleMapper.selectArticlesByContent(articleContent);
    }

    @Override
    public List<ArticleDetailDto> selectArticlesByNickname(String nickname) {
        return articleMapper.selectArticlesByNickname(nickname);
    }

    @Override
    public int articleDeleteRequest(int articleId) {
        return articleMapper.articleDeleteRequest(articleId);
    }

    @Override
    public ArticleDetailDto getArticleDetailById(int articleId) {
        return articleMapper.getArticleDetailById(articleId);
    }

    @Override
    public int updateArticle(ArticleUpdateDto articleUpdateDto) {
        return articleMapper.updateArticle(articleUpdateDto);
    }

    @Transactional
    @Override
    public int recommendArticle(ArticleRecommendReqDto articleRecommendReqDto) {
        // 추천했는지 확인
        int isRecommendation = articleMapper.checkRecommend(articleRecommendReqDto);
        // 총 개수를 확인해서 확인하는 것으로 교체
        if (isRecommendation == 1) {
            articleMapper.deleteRecommend(articleRecommendReqDto);
        } else {
            articleMapper.insertRecommend(articleRecommendReqDto);
        }
        int count = articleMapper.countRecommend(articleRecommendReqDto);
        articleMapper.updateRecommendArticle(count, articleRecommendReqDto);
        return isRecommendation;
    }

    @Override
    public int checkRecommendArticle(int articleId, int memberId) {
        ArticleRecommendReqDto articleRecommendReqDto = ArticleRecommendReqDto.builder()
                .articleId(articleId)
                .memberId(memberId)
                .build();
        return articleMapper.checkRecommend(articleRecommendReqDto);
    }
}
