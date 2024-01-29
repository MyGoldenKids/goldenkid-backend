package com.ehours.goldenchild.article.mapper;

import com.ehours.goldenchild.article.dto.ArticleUpdateDto;
import org.apache.ibatis.jdbc.SQL;

public class ArticleUpdateProvider {
    public String updateArticle(ArticleUpdateDto articleUpdateDto) {
        return new SQL() {{
            UPDATE("article");
            if (articleUpdateDto.getFileListId() != 0) {
                SET("file_list_id = #{fileListId}");
            }
            SET("article_title = #{articleTitle}");
            SET("article_content = #{articleContent}");
            SET("modified_at = CURRENT_TIMESTAMP");
            WHERE("article_id = #{articleId}");
        }}.toString();
    }
}