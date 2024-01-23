package com.ehours.goldenchild.child;

import com.ehours.goldenchild.child.dto.ChildDetailResDto;
import com.ehours.goldenchild.child.dto.ChildModifyReqDto;
import com.ehours.goldenchild.child.dto.ChildRegisterReqDto;
import com.ehours.goldenchild.child.service.ChildService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class ChildTests {
    @Autowired
    ChildService childService;

    @Test
    @Transactional
    void registerChild() {
        ChildRegisterReqDto childRegisterReqDto = ChildRegisterReqDto.builder()
                .childName("금쪽이")
                .childBirth("2022-10-01")
                .childGender(false)
                .memberId(6)
                .build();
        int retValue = childService.registerChild(childRegisterReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(childRegisterReqDto.toString());
    }

    @Test
    @Transactional
    void detailChild() {
        ChildDetailResDto retValue = childService.detailChild(1);
        log.info(retValue.toString());
    }

    @Test
    @Transactional
    void modifyChild() {
        ChildModifyReqDto childModifyReqDto = ChildModifyReqDto.builder()
                .childId(1)
                .childName("난가?")
                .childGender(true)
                .childBirth("2024-01-01")
                .build();
        int retValue = childService.modifyChild(childModifyReqDto);
        Assertions.assertThat(retValue).isEqualTo(1);
        log.info(childModifyReqDto.toString());
    }

    @Test
    @Transactional
    void deleteChild() {
        int retValue = childService.deleteChild(2);
        Assertions.assertThat(retValue).isEqualTo(1);

    }
}
