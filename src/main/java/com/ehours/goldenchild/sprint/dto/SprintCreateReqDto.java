package com.ehours.goldenchild.sprint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SprintCreateReqDto {
    private int sprintId;
    private int memberId;
    private String sprintTitle;
    private String startDate;
    private String endDate;
}
