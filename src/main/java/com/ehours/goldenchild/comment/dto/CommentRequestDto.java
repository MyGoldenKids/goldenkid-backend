package com.ehours.goldenchild.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentRequestDto {
    private int commentId;
    private int memberId;
    private String content;
}