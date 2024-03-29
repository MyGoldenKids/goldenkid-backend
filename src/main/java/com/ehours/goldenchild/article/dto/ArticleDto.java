package com.ehours.goldenchild.article.dto;

import lombok.Data;

@Data
public class ArticleDto {
    private int articleId;
    private int memberId;
    private int fileListId;
    private String articleTitle;
    private String articleContent;
    private String createdAt;
    private String modifiedAt;
    private int hit;
    private int recommendCount;
    private boolean articleStatus;
}
