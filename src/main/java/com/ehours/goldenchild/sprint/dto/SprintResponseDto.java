package com.ehours.goldenchild.sprint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SprintResponseDto {
    private int sprintId;
    private String sprintTitle;
    private boolean sprintStatus;
    private String createdAt;
    private String startDate;
    private String endDate;
}
