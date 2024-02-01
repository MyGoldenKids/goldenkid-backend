package com.ehours.goldenchild.sprint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SprintStatusReqDto {
    private int memberId;
    private boolean sprintStatus;
}
