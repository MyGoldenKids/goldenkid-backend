package com.ehours.goldenchild.article.controller;

import com.ehours.goldenchild.article.dto.ArticleDto;
import com.ehours.goldenchild.article.dto.ArticleReqDto;
import com.ehours.goldenchild.article.service.ArticleService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/write")
    public ResponseEntity<String> writeArticle(ArticleReqDto articleReqDto) {
        int retValue = articleService.writeArticle(articleReqDto);
        if (retValue == 1) {
            return ResponseEntity.ok("게시판 글 등록 성공!");
        } else return ResponseEntity.internalServerError().body("게시판 글 등록 실패..");
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllArticles() {
        List<ArticleDto> articleList = articleService.getAllArticles();
        Map<String, Object> result = new HashMap<>();
        result.put("data", articleList);
        if (articleList != null) return ResponseEntity.ok(result);
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    @PutMapping("/delete")
    public ResponseEntity<String> articleDeleteRequest(int articleId) {
        int retValue = articleService.articleDeleteRequest(articleId);
        if (retValue == 1) return ResponseEntity.ok("삭제 요청 성공");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 요청 실패..");
    }
}
