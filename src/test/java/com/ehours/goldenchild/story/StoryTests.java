package com.ehours.goldenchild.story;

import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import com.ehours.goldenchild.sprint.service.SprintService;
import com.ehours.goldenchild.story.dto.StoryCreateReqDto;
import com.ehours.goldenchild.story.dto.StoryDetailResDto;
import com.ehours.goldenchild.story.dto.StoryStatusReqDto;
import com.ehours.goldenchild.story.service.StoryService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StoryTests {
    @Autowired
    private MemberService memberService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SprintService sprintService;
    @Autowired
    private StoryService storyService;

    private MemberSignUpReqDto memberSignUpReqDto;
    private MemberLoginResDto login;
    private SprintCreateReqDto sprintCreateReqDto;
    private StoryCreateReqDto storyCreateReqDto, storyCreateReqDto2, storyCreateReqDto3;

    @BeforeAll
    public void setUp() {
        String id = "test54321@kakao.com";
        String pw = "password1234";
        memberSignUpReqDto = MemberSignUpReqDto.builder()
                .memberId(id)
                .password(pw)
                .nickname("카카오")
                .phoneNumber("010-1234-5678")
                .build();
        memberService.signup(memberSignUpReqDto);
        MemberLoginReqDto memberLoginReqDto = MemberLoginReqDto.builder()
                .memberId(id)
                .password(pw)
                .build();
        login = memberService.login(memberLoginReqDto);
        sprintCreateReqDto = SprintCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .sprintTitle("test")
                .startDate("2024-01-31")
                .endDate("2024-02-10")
                .build();
        int retValue = sprintService.createSprint(sprintCreateReqDto);
        log.info(sprintCreateReqDto.toString());
        System.out.println(retValue);
        Assertions.assertThat(retValue).isEqualTo(1);

        storyCreateReqDto = StoryCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .storyContent("스토리")
                .sprintId(sprintCreateReqDto.getSprintId())
                .storyPoint(5)
                .build();
        storyService.createStory(storyCreateReqDto);

//        System.out.println(retValue);
        log.info(storyCreateReqDto.toString());

    }

    @AfterAll
    public void deleteSetup() {
        jdbcTemplate.update("DELETE FROM member WHERE no = ?", login.getMemberNo());
    }


//    @Test
//    @Transactional
//    void createStory() {
//        StoryCreateReqDto create = StoryCreateReqDto.builder()
//                .memberId(login.getMemberNo())
//                .storyContent("스토리")
//                .sprintId(sprintCreateReqDto.getSprintId())
//                .storyPoint(5)
//                .build();
//        int retValue = storyService.createStory(create);
//        System.out.println(retValue);
////        log.info(create.toString());
//    }

    @Test
    @Transactional
    void updateStatus() {
        StoryStatusReqDto storyStatusReqDto = StoryStatusReqDto.builder()
                .storyStatus(1)
                .memberId(login.getMemberNo())
                .build();
        log.info(storyService.getStoryById(storyCreateReqDto.getStoryId(), login.getMemberNo()).toString());
        int retVal = storyService.updateStoryStatus(storyCreateReqDto.getStoryId(), storyStatusReqDto);
        log.info(storyService.getStoryById(storyCreateReqDto.getStoryId(), login.getMemberNo()).toString());
        log.info(storyCreateReqDto.toString());
        Assertions.assertThat(retVal).isEqualTo(1);
    }

    @Test
    @Transactional
    void getStoryBySprintId() {
        storyCreateReqDto3 = StoryCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .storyContent("스토리3")
                .sprintId(sprintCreateReqDto.getSprintId())
                .storyPoint(5)
                .build();
        storyService.createStory(storyCreateReqDto3);
        List<StoryDetailResDto> storyDetailResDtoList = storyService.getStoryBySprintId(sprintCreateReqDto.getSprintId(), login.getMemberNo());
        log.info(storyDetailResDtoList.toString());
    }

    @Test
    @Transactional
    void deleteStory() {
        storyCreateReqDto2 = StoryCreateReqDto.builder()
                .memberId(login.getMemberNo())
                .storyContent("스토리2")
                .sprintId(sprintCreateReqDto.getSprintId())
                .storyPoint(5)
                .build();
        storyService.createStory(storyCreateReqDto2);
        int count = storyService.getStoryBySprintId(storyCreateReqDto.getSprintId(), login.getMemberNo()).size();
        log.info(storyService.getStoryBySprintId(storyCreateReqDto2.getStoryId(), login.getMemberNo()).toString());
        storyService.deleteStory(storyCreateReqDto2.getStoryId(), login.getMemberNo());
//        log.info(storyService.getStoryBySprintId(storyCreateReqDto2.getStoryId(), login.getMemberNo()).toString());

        Assertions.assertThat(storyService.getStoryBySprintId(storyCreateReqDto.getSprintId(), login.getMemberNo()).size()).isEqualTo(count - 1);
    }


}
