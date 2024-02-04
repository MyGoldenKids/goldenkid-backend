package com.ehours.goldenchild.comment.service;

import com.ehours.goldenchild.comment.dto.CommentDetailResDto;
import com.ehours.goldenchild.comment.dto.CommentRequestDto;

import java.util.List;

public interface CommentService {
    List<CommentDetailResDto> getCommentByArticleId(int articleId);
    int writeComment(int articleId, CommentRequestDto commentRequestDto);
    int updateComment(int commentId, CommentRequestDto commentRequestDto);
    int deleteComment(int commentId, int memberId);
}
