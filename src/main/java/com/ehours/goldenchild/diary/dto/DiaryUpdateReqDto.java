package com.ehours.goldenchild.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DiaryUpdateReqDto {
    private int diaryId;
    private String diaryTitle;
    private Integer fileListId;
    private int memberId;
    private int childId;
    private String diaryContent;
    private String diaryReview;

}
