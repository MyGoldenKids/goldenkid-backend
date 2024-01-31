package com.ehours.goldenchild.sprint;

import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import com.ehours.goldenchild.sprint.service.SprintService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class SprintTests {
    @Autowired
    SprintService sprintService;

    @Test
    @Transactional
    void createSprint() {
        SprintCreateReqDto sprintCreateReqDto = SprintCreateReqDto.builder()
                .memberId(1)
                .sprintTitle("테스트제목")
                .startDate("2024-01-31")
                .endDate("2024-02-10")
                .build();
        int retValue = sprintService.createSprint(sprintCreateReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
    }
}
