package com.ehours.goldenchild.comment;

import com.ehours.goldenchild.article.dto.ArticleReqDto;
import com.ehours.goldenchild.article.service.ArticleService;
import com.ehours.goldenchild.comment.dto.CommentDetailResDto;
import com.ehours.goldenchild.comment.dto.CommentRequestDto;
import com.ehours.goldenchild.comment.service.CommentService;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
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

import java.util.List;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentTests {
    @Autowired
    private MemberService memberService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private MemberLoginResDto login;
    private ArticleReqDto articleReqDto;
    private CommentRequestDto testComment;

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

        // 게시물 작성
        articleReqDto = ArticleReqDto.builder()
                .articleTitle("댓글 잘나오냐?")
                .articleContent("제발 나와라")
                .memberId(login.getMemberNo())
                .fileListId(0)
                .build();
        articleService.writeArticle(articleReqDto);

        testComment = CommentRequestDto.builder()
                .memberId(login.getMemberNo())
                .content("드가자")
                .build();
        commentService.writeComment(articleReqDto.getArticleId(), testComment);

    }

    @AfterAll
    public void deleteSetup() {
        jdbcTemplate.update("DELETE FROM article WHERE member_id = ?", login.getMemberNo());
        jdbcTemplate.update("DELETE FROM member WHERE no = ?", login.getMemberNo());
    }

    @Test
    @Transactional
    void getComments() {
        List<CommentDetailResDto> commentDetailResDtoList = commentService.getCommentByArticleId(login.getMemberNo());
        log.info(commentDetailResDtoList.toString());
    }

    @Test
    @Transactional
    void writeComment() {
        int count = commentService.getCommentByArticleId(articleReqDto.getArticleId()).size();
        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
                .memberId(login.getMemberNo())
                .content("드가자")
                .build();
        int retValue = commentService.writeComment(articleReqDto.getArticleId(), commentRequestDto);
        Assertions.assertThat(commentService.getCommentByArticleId(articleReqDto.getArticleId()).size()).isEqualTo(count + 1);
        log.info(commentService.getCommentByArticleId(articleReqDto.getArticleId()).toString());
    }

    @Test
    @Transactional
    void updateComment() {
        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
                .memberId(login.getMemberNo())
                .content("수정 전")
                .build();
        commentService.writeComment(articleReqDto.getArticleId(), commentRequestDto);
        log.info(commentService.getCommentByArticleId(articleReqDto.getArticleId()).toString());
        log.info(commentRequestDto.toString());
        CommentRequestDto updateCommentReq = CommentRequestDto.builder()
                .memberId(login.getMemberNo())
                .content("수정 후")
                .build();
        int retValue = commentService.updateComment(testComment.getCommentId(), updateCommentReq);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(commentService.getCommentByArticleId(articleReqDto.getArticleId()).toString());
    }

    @Test
    @Transactional
    void deleteComment() {
        int retValue = commentService.deleteComment(testComment.getCommentId(), login.getMemberNo());
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(commentService.getCommentByArticleId(articleReqDto.getArticleId()).toString());
    }
}
