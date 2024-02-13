package com.ehours.goldenchild.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ArticleRecommendReqDto {
    private int articleId;
    private int memberId;
}
