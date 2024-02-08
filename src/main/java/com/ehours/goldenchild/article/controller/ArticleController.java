package com.ehours.goldenchild.article.controller;

import com.ehours.goldenchild.article.dto.ArticleDetailDto;
import com.ehours.goldenchild.article.dto.ArticleDto;
import com.ehours.goldenchild.article.dto.ArticleReqDto;
import com.ehours.goldenchild.article.dto.ArticleUpdateDto;
import com.ehours.goldenchild.article.service.ArticleService;
import com.ehours.goldenchild.common.ResponseResource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/write")
    public ResponseEntity<Map<String, Object>> writeArticle(@RequestBody ArticleReqDto articleReqDto) {
        int retValue = articleService.writeArticle(articleReqDto);
        if (retValue == 1) return ResponseResource.handleSuccess(articleReqDto.getArticleId(), "게시판 글 등록 성공!");
        else return ResponseResource.handleError("게시판 글 등록 실패..");
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllArticles(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        List<ArticleDetailDto> articleList = articleService.getAllArticles(page, size);
        if (articleList != null) return ResponseResource.handleSuccess(articleList, "게시판 리스트 조회 성공!");
        else return ResponseResource.handleError("게시판 리스트 조회 실패..");
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchArticles(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String nickname) {

        List<ArticleDetailDto> articleList = null;
        String message = null;

        if (Stream.of(title, content, nickname).filter(Objects::nonNull).count() > 1) {
            return ResponseResource.handleError("하나의 검색 조건만 사용해주세요.");
        }

        if (title != null) {
            articleList = articleService.selectArticlesByTitle(title);
            message = "제목으로 조회 성공";
        } else if (content != null) {
            articleList = articleService.selectArticlesByContent(content);
            message = "내용으로 조회 성공";
        } else if (nickname != null) {
            articleList = articleService.selectArticlesByNickname(nickname);
            message = "닉네임으로 조회 성공";
        } else {
            return ResponseResource.handleError("검색 조건을 제공해주세요.");
        }

        return ResponseResource.handleSuccess(articleList, message);
    }

    @GetMapping("/detail/{articleId}")
    public ResponseEntity<Map<String, Object>> getArticleById(@PathVariable int articleId) {
        ArticleDetailDto articleDetailDto = articleService.getArticleDetailById(articleId);
        if (articleDetailDto != null) return ResponseResource.handleSuccess(articleDetailDto, "게시글 조회 성공");
        else return ResponseResource.handleError("게시글 조회 실패..");
    }

    @PutMapping("/modify")
    public ResponseEntity<Map<String, Object>> updateArticle(@RequestBody ArticleUpdateDto articleUpdateDto) {
        int retValue = articleService.updateArticle(articleUpdateDto);
        if (retValue == 1) return ResponseResource.handleSuccess(articleUpdateDto.getArticleId(), "게시글 수정 성공");
        else return ResponseResource.handleError("게시글 수정 실패..");
    }
}
