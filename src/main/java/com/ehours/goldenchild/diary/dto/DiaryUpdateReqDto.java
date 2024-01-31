package com.ehours.goldenchild.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DiaryUpdateReqDto {
    private int memberId;
    private Integer diaryId;
    private String diaryTitle;
    private String diaryContent;
    private String diaryReview;
    private Integer fileListId;
}
