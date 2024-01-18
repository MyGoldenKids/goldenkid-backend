package com.ehours.goldenchild.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleUpdateDto {
    private int articleId;
    private int fileListId;
    private String articleTitle;
    private String articleContent;
}
