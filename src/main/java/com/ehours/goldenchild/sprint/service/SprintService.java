package com.ehours.goldenchild.sprint.service;

import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import com.ehours.goldenchild.sprint.dto.SprintResponseDto;
import java.util.List;

public interface SprintService {
    int createSprint(SprintCreateReqDto sprintCreateReqDto);
    List<SprintResponseDto> getSprintList(int memberId);
}
