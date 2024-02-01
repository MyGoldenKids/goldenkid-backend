package com.ehours.goldenchild.sprint.service;

import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import com.ehours.goldenchild.sprint.dto.SprintResponseDto;
import com.ehours.goldenchild.sprint.dto.SprintStatusReqDto;
import com.ehours.goldenchild.sprint.mapper.SprintMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService{
    private final SprintMapper sprintMapper;
    @Override
    public int createSprint(SprintCreateReqDto sprintCreateReqDto) {
        return sprintMapper.createSprint(sprintCreateReqDto);
    }

    @Override
    public List<SprintResponseDto> getSprintList(int memberId) {
        return sprintMapper.getSprintList(memberId);
    }

    @Override
    public int updateSprintStatus(int sprintId, SprintStatusReqDto sprintStatusReqDto) {
        return sprintMapper.updateSprintStatus(sprintId, sprintStatusReqDto);
    }
}
