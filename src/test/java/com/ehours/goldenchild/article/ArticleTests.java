package com.ehours.goldenchild.article;

import com.ehours.goldenchild.article.dto.ArticleDetailDto;
import com.ehours.goldenchild.article.dto.ArticleDto;
import com.ehours.goldenchild.article.dto.ArticleReqDto;
import com.ehours.goldenchild.article.dto.ArticleUpdateDto;
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
                .memberId(1)
                .fileListId(1)
                .articleTitle("테스트용 게시글 제목")
                .articleContent("테스트용 게시글 내용")
                .build();
        int retValue = articleService.writeArticle(articleReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
    }

    @Test
    @Transactional
    void articleInsertWithoutFileListIdTest() {
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .memberId(1)
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
                .memberId(1)
                .fileListId(1)
                .articleTitle("테스트용 게시글 제목")
                .articleContent("테스트용 게시글 내용")
                .build();
        articleService.writeArticle(articleReqDto);
        List<ArticleDetailDto> list = articleService.getAllArticles();
        log.debug(list.toString());
        Assertions.assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    void getArticleByIdTest() {
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .memberId(1)
                .fileListId(1)
                .articleTitle("테스트용 게시글 제목")
                .articleContent("테스트용 게시글 내용")
                .build();
        articleService.writeArticle(articleReqDto);
        Assertions.assertThat(articleService.getArticleDetailById(articleReqDto.getArticleId())).isNotNull();
        int retValue = articleService.articleDeleteRequest(articleReqDto.getArticleId());
        ArticleDetailDto articleDetailDto = articleService.getArticleDetailById(articleReqDto.getArticleId());
        Assertions.assertThat(articleDetailDto).isNull();
    }

    @Test
    @Transactional
    void deleteReqTest() {
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .memberId(1)
                .fileListId(1)
                .articleTitle("테스트용 게시글 제목")
                .articleContent("테스트용 게시글 내용")
                .build();
        articleService.writeArticle(articleReqDto);
        int retValue = articleService.articleDeleteRequest(articleReqDto.getArticleId());
        Assertions.assertThat(retValue).isEqualTo(1);
    }

    @Test
    @Transactional
    void modifyTest() {
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .memberId(1)
                .fileListId(1)
                .articleTitle("테스트용 게시글 제목")
                .articleContent("테스트용 게시글 내용")
                .build();
        articleService.writeArticle(articleReqDto);
        ArticleDetailDto articleDetailDto = articleService.getArticleDetailById(articleReqDto.getArticleId());
        ArticleUpdateDto articleUpdateDto = new ArticleUpdateDto(articleDetailDto.getArticleId(),
                articleDetailDto.getFileListId(),
                "바뀐 제목",
                "바뀐 내용");
        articleService.updateArticle(articleUpdateDto);
        ArticleDetailDto articleDetailDto1 = articleService.getArticleDetailById(articleDetailDto.getArticleId());
        Assertions.assertThat(articleUpdateDto.getArticleTitle()).isEqualTo("바뀐 제목");
        Assertions.assertThat(articleUpdateDto.getArticleContent()).isEqualTo("바뀐 내용");
        log.info(articleUpdateDto.toString());
    }

    @Test
    @Transactional
    void modifyWithoutFileListIdTest() {
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .memberId(1)
                .articleTitle("테스트용 게시글 제목")
                .articleContent("테스트용 게시글 내용")
                .build();
        articleService.writeArticle(articleReqDto);
        ArticleDetailDto articleDetailDto = articleService.getArticleDetailById(articleReqDto.getArticleId());
        ArticleUpdateDto articleUpdateDto = new ArticleUpdateDto(articleDetailDto.getArticleId(),
                articleDetailDto.getFileListId(),
                "바뀐 제목",
                "바뀐 내용");
        articleService.updateArticle(articleUpdateDto);
        ArticleDetailDto articleDetailDto1 = articleService.getArticleDetailById(articleDetailDto.getArticleId());
        Assertions.assertThat(articleUpdateDto.getArticleTitle()).isEqualTo("바뀐 제목");
        Assertions.assertThat(articleUpdateDto.getArticleContent()).isEqualTo("바뀐 내용");
        log.info(articleUpdateDto.toString());
    }
}
