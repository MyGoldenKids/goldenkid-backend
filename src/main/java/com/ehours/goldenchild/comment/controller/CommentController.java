package com.ehours.goldenchild.comment.controller;

import com.ehours.goldenchild.comment.dto.CommentDetailResDto;
import com.ehours.goldenchild.comment.service.CommentService;
import com.ehours.goldenchild.common.ResponseResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/list/{articleId}")
    public ResponseEntity<Map<String, Object>> getCommentByArticleId(@PathVariable int articleId) {
        List<CommentDetailResDto> commentDetailResDtoList = commentService.getCommentByArticleId(articleId);
        return ResponseResource.handleSuccess(commentDetailResDtoList, "댓글 조회 성공");
    }
}
