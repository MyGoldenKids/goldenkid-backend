package com.ehours.goldenchild.article.dto;

import lombok.Data;

@Data
public class ArticleDetailDto {
    private int articleId;
    private int memberId;
    private String nickname;
    private int fileListId;
    private String articleTitle;
    private String articleContent;
    private String createdAt;
    private String modifiedAt;
    private int hit;
    private int recommendCount;
}
