package com.ehours.goldenchild.child.service;

import com.ehours.goldenchild.child.dto.ChildDetailResDto;
import com.ehours.goldenchild.child.dto.ChildModifyReqDto;
import com.ehours.goldenchild.child.dto.ChildRegisterReqDto;
import java.util.List;

public interface ChildService {
    int registerChild(ChildRegisterReqDto childRegisterReqDto);
    ChildDetailResDto detailChild(int childId);
    int modifyChild(ChildModifyReqDto childModifyReqDto);
    int deleteChild(int childId);
    List<ChildDetailResDto> getMyChild(int memberId);
}
