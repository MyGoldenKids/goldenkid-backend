package com.ehours.goldenchild.story.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StoryStatusReqDto {
    private int storyStatus;
    private int memberId;
}
