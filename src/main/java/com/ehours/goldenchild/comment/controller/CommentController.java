package com.ehours.goldenchild.comment.controller;

import com.ehours.goldenchild.comment.dto.CommentDetailResDto;
import com.ehours.goldenchild.comment.dto.CommentRequestDto;
import com.ehours.goldenchild.comment.service.CommentService;
import com.ehours.goldenchild.common.ResponseResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/write/{articleId}")
    public ResponseEntity<Map<String, Object>> writeComment(@PathVariable int articleId, @RequestBody CommentRequestDto commentRequestDto) {
        int retValue = commentService.writeComment(articleId, commentRequestDto);
        if (retValue == 1) return ResponseResource.handleSuccess(commentRequestDto.getCommentId(), "입력 성공");
        else return ResponseResource.handleError("입력 실패");
    }

    @PatchMapping("/modify/{commentId}")
    public ResponseEntity<Map<String, Object>> updateComment(@PathVariable int commentId, @RequestBody CommentRequestDto commentRequestDto) {
        int retValue = commentService.updateComment(commentId, commentRequestDto);
        if (retValue == 1) return ResponseResource.handleSuccess(commentRequestDto.getCommentId(), "댓글 수정 성공");
        else return ResponseResource.handleError("댓글 수정 실패");
    }
}
