package com.ehours.goldenchild.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentDetailResDto {
    private int commentId;
    private int articleId;
    private int memberId;
    private String nickname;
    private String content;
    private String createdAt;
    private String modifiedAt;
}
