package com.ehours.goldenchild.activity;

import com.ehours.goldenchild.activity.dto.RecommendActivityResDto;
import com.ehours.goldenchild.activity.service.ActivityService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@Slf4j
public class ActivityTests {
    @Autowired
    private ActivityService activityService;

    @Test
    @Transactional
    void getRecommendStory() {
        List<RecommendActivityResDto> StoryRecommendResDtoList = activityService.getActivity();
        log.info(StoryRecommendResDtoList.toString());

         assertThat(StoryRecommendResDtoList).isNotNull();
    }
}
