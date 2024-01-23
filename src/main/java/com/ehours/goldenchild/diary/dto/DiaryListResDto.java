package com.ehours.goldenchild.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DiaryListResDto {
    private int diaryId;
    private String diaryTitle;
    private String createdAt;

}
