package com.ehours.goldenchild.article;

import com.ehours.goldenchild.article.dto.*;
import com.ehours.goldenchild.article.service.ArticleService;
import com.ehours.goldenchild.file.dto.FileListIdDto;
import com.ehours.goldenchild.file.mapper.FileMapper;
import com.ehours.goldenchild.file.service.FileService;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArticleTests {
    @Autowired
    ArticleService articleService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    MemberService memberService;
    @Autowired
    FileMapper fileMapper;
    private MemberLoginResDto login;
    private FileListIdDto fileListIdDto;

    @BeforeAll
    public void setUp() {
        String id = "test54321@kakao.com";
        String pw = "password1234";
        MemberSignUpReqDto memberSignUpReqDto = MemberSignUpReqDto.builder()
                .memberId(id)
                .password(pw)
                .nickname("카카오")
                .phoneNumber("010-1234-5678")
                .build();
        memberService.signup(memberSignUpReqDto);
        MemberLoginReqDto memberLoginReqDto = MemberLoginReqDto.builder()
                .memberId(id)
                .password(pw)
                .build();
        login = memberService.login(memberLoginReqDto);
        fileListIdDto = new FileListIdDto();
        fileMapper.makeFileList(fileListIdDto);
    }

    @AfterAll
    public void deleteSetup() {
        jdbcTemplate.update("DELETE FROM member WHERE no = ?", login.getMemberNo());
        jdbcTemplate.update("DELETE FROM file_list WHERE file_list_id = ?", fileListIdDto.getFileListId());
    }
    @Test
    @Transactional
    void articleInsertTest() {
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .memberId(login.getMemberNo())
                .fileListId(fileListIdDto.getFileListId())
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
                .memberId(login.getMemberNo())
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
                .memberId(login.getMemberNo())
                .fileListId(fileListIdDto.getFileListId())
                .articleTitle("테스트용 게시글 제목")
                .articleContent("테스트용 게시글 내용")
                .build();
        articleService.writeArticle(articleReqDto);
        List<ArticleDetailDto> list = articleService.getAllArticles(0, 10);
        log.debug(list.toString());
        System.out.println(list.size());
        Assertions.assertThat(list).isNotNull();
    }

    @Test
    @Transactional
    void getArticleByIdTest() {
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .memberId(login.getMemberNo())
                .fileListId(fileListIdDto.getFileListId())
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
                .memberId(login.getMemberNo())
                .fileListId(fileListIdDto.getFileListId())
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
                .memberId(login.getMemberNo())
                .fileListId(fileListIdDto.getFileListId())
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
                .memberId(login.getMemberNo())
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
    void getByTitle() {
        List<ArticleDetailDto> articleDetailDtos = articleService.selectArticlesByTitle("테스트");
        log.info(articleDetailDtos.toString());
    }

    @Test
    @Transactional
    void getByContent() {
        List<ArticleDetailDto> articleDetailDtos = articleService.selectArticlesByContent("1111111");
        log.info(articleDetailDtos.toString());
    }
    @Test
    @Transactional
    void getByNickname() {
        List<ArticleDetailDto> articleDetailDtos = articleService.selectArticlesByNickname("없");
        log.info(articleDetailDtos.toString());
    }

    @Test
    @Transactional
    void voteArticle() {
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .memberId(login.getMemberNo())
                .fileListId(fileListIdDto.getFileListId())
                .articleTitle("테스트용 게시글 제목")
                .articleContent("테스트용 게시글 내용")
                .build();
        articleService.writeArticle(articleReqDto);
        ArticleRecommendReqDto articleRecommendReqDto = ArticleRecommendReqDto.builder()
                .articleId(articleReqDto.getArticleId())
                .memberId(login.getMemberNo())
                .build();

        // 추천
        int retValue = articleService.recommendArticle(articleRecommendReqDto);
        ArticleDetailDto articleDetailDto = articleService.getArticleDetailById(articleReqDto.getArticleId());
        Assertions.assertThat(articleDetailDto.getRecommendCount()).isEqualTo(1);
        Assertions.assertThat(retValue).isEqualTo(0);


        // 추천 취소
        retValue = articleService.recommendArticle(articleRecommendReqDto);
        articleDetailDto = articleService.getArticleDetailById(articleReqDto.getArticleId());
        Assertions.assertThat(articleDetailDto.getRecommendCount()).isEqualTo(0);
        Assertions.assertThat(retValue).isEqualTo(1);
    }

}
