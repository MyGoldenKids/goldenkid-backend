package com.ehours.goldenchild.child.service;

import com.ehours.goldenchild.child.dto.ChildRegisterReqDto;
import com.ehours.goldenchild.child.mapper.ChildMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService{
    private final ChildMapper childMapper;
    @Override
    public int registerChild(ChildRegisterReqDto childRegisterReqDto) {
        return childMapper.registerChild(childRegisterReqDto);
    }
}
