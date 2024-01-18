package com.ehours.goldenchild.article;

import com.ehours.goldenchild.article.dto.ArticleDto;
import com.ehours.goldenchild.article.dto.ArticleReqDto;
import com.ehours.goldenchild.article.service.ArticleService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class ArticleTests {
    @Autowired
    ArticleService articleService;

//    private String member_id;
//    private int file_list_id;
//    private String article_title;
//    private String article_content;
    @Test
    @Transactional
    void articleInsertTest() {
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .memberId(4)
                .fileListId(1)
                .articleTitle("테스트용 게시글 제목")
                .articleContent("테스트용 게시글 내용")
                .build();
        int retValue = articleService.writeArticle(articleReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
    }

    @Test
    @Transactional
    void getAllArticlesTest() {
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .memberId(4)
                .fileListId(1)
                .articleTitle("테스트용 게시글 제목")
                .articleContent("테스트용 게시글 내용")
                .build();
        articleService.writeArticle(articleReqDto);
        List<ArticleDto> list = articleService.getAllArticles();
        log.debug(list.toString());
        Assertions.assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    void deleteReqTest() {
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .memberId(4)
                .fileListId(1)
                .articleTitle("테스트용 게시글 제목")
                .articleContent("테스트용 게시글 내용")
                .build();
        articleService.writeArticle(articleReqDto);
        int retValue = articleService.articleDeleteRequest(articleReqDto.getArticleId());
        Assertions.assertThat(retValue).isEqualTo(1);
    }
}
