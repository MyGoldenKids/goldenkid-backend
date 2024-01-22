package com.ehours.goldenchild.child.service;

import com.ehours.goldenchild.child.dto.ChildDetailResDto;
import com.ehours.goldenchild.child.dto.ChildModifyReqDto;
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

    @Override
    public ChildDetailResDto detailChild(int childId) {
        return childMapper.detailChild(childId);
    }

    @Override
    public int modifyChild(ChildModifyReqDto childModifyReqDto) {
        return childMapper.modifyChild(childModifyReqDto);
    }

}
