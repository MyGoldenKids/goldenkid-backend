package com.ehours.goldenchild.child.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChildRegisterReqDto {
    private String childName;
    private String childBirth;
    private boolean childGender;
    private int memberId;
    private int fileId;
}
