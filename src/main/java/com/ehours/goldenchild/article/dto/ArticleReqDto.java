package com.ehours.goldenchild.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ArticleReqDto {
    private int articleId;
    private int memberId;
    private int fileListId;
    private String articleTitle;
    private String articleContent;
}
