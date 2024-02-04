package com.ehours.goldenchild.comment.service;

import com.ehours.goldenchild.comment.dto.CommentDetailResDto;

import java.util.List;

public interface CommentService {
    List<CommentDetailResDto> getCommentByArticleId(int articleId);
}
