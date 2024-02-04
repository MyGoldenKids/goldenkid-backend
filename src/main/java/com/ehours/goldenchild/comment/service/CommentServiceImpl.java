package com.ehours.goldenchild.comment.service;

import com.ehours.goldenchild.comment.dto.CommentDetailResDto;
import com.ehours.goldenchild.comment.dto.CommentRequestDto;
import com.ehours.goldenchild.comment.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDetailResDto> getCommentByArticleId(int articleId) {
        return commentMapper.getCommentByArticleId(articleId);
    }

    @Override
    public int writeComment(int articleId, CommentRequestDto commentRequestDto) {
        return commentMapper.writeComment(articleId, commentRequestDto);
    }

    @Override
    public int updateComment(int commentId, CommentRequestDto commentRequestDto) {
        return commentMapper.updateComment(commentId, commentRequestDto);
    }

    @Override
    public int deleteComment(int commentId, int memberId) {
        return commentMapper.deleteComment(commentId, memberId);
    }
}
