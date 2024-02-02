package com.ehours.goldenchild.sprint.service;

import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import com.ehours.goldenchild.sprint.dto.SprintModifyReqDto;
import com.ehours.goldenchild.sprint.dto.SprintResponseDto;
import com.ehours.goldenchild.sprint.dto.SprintStatusReqDto;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface SprintService {
    int createSprint(SprintCreateReqDto sprintCreateReqDto);
    List<SprintResponseDto> getSprintList(int memberId);
    int updateSprintStatus(int sprintId, SprintStatusReqDto sprintStatusReqDto);
    int updateSprint(int sprintId, SprintModifyReqDto sprintModifyReqDto);
    int deleteSprint(int sprintId, int memberId);
}
