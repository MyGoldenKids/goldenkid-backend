package com.ehours.goldenchild.article.mapper;

import com.ehours.goldenchild.article.dto.ArticleReqDto;
import com.ehours.goldenchild.article.dto.ArticleUpdateDto;
import org.apache.ibatis.jdbc.SQL;

public class ArticleWriteProvider {

    public String writeArticle(ArticleReqDto articleReqDto) {
        return new SQL() {{
            INSERT_INTO("article");
            if (articleReqDto.getFileListId() != 0) {
                VALUES("file_list_id", "#{fileListId}");
            }
            VALUES("member_id", "#{memberId}");
            VALUES("article_title", "#{articleTitle}");
            VALUES("article_content", "#{articleContent}");
        }}.toString();
    }
}
