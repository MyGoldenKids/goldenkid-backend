package com.ehours.goldenchild.story.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StoryDetailResDto {
    private int storyId;
    private int memberId;
    private int sprintId;
    private String storyContent;
    private int storyStatus;
    private int storyPoint;
    private String createdAt;
}
