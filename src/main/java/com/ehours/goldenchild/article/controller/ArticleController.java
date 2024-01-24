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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "게시판 글 등록 성공!");
        else return ResponseResource.handleError("게시판 글 등록 실패..");
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllArticles() {
        List<ArticleDetailDto> articleList = articleService.getAllArticles();
        if (articleList != null) return ResponseResource.handleSuccess(articleList, "게시판 리스트 조회 성공!");
        else return ResponseResource.handleError("게시판 리스트 조회 실패..");
    }

    @PutMapping("/delete/{articleId}")
    public ResponseEntity<Map<String, Object>> articleDeleteRequest(@PathVariable int articleId) {
        int retValue = articleService.articleDeleteRequest(articleId);
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "삭제 요청 성공!");
        else return ResponseResource.handleError("삭제 요청 실패..");
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
        if (retValue == 1) return ResponseResource.handleSuccess(retValue, "게시글 수정 성공");
        else return ResponseResource.handleError("게시글 수정 실패..");
    }
}
