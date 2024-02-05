package com.ehours.goldenchild.story.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StoryRequestDto {
    private int memberId;
    private String storyContent;
    private int storyPoint;
    private int storyId;
    private int sprintId;
}
