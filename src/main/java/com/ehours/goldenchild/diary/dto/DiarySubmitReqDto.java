package com.ehours.goldenchild.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DiarySubmitReqDto {
    private int diaryId;
    private int fileListId;
    private String diaryTitle;
    private String diaryContent;
    private String diaryReview;
}
