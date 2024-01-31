package com.ehours.goldenchild.sprint.service;

import com.ehours.goldenchild.sprint.dto.SprintCreateReqDto;
import com.ehours.goldenchild.sprint.mapper.SprintMapper;
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
}
