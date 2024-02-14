package com.ehours.goldenchild.activity.service;

import com.ehours.goldenchild.activity.dto.RecommendActivityResDto;
import com.ehours.goldenchild.activity.mapper.ActivityMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{
    private final ActivityMapper activityMapper;
    @Override
    public List<RecommendActivityResDto> getActivity() {
        return activityMapper.recommendActivity();
    }
}
