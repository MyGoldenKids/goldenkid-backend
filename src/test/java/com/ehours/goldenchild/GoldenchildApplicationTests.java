package com.ehours.goldenchild;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
class GoldenchildApplicationTests {
	@Autowired
	MemberService memberService;
	private static final int testMemberNo = 50;
	private static final String testMemberId = "test@daum.net";
	private static final String testPassword = "1234";

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	void signupTest() {
		MemberSignUpReqDto memberReqDto = MemberSignUpReqDto.builder()
				.memberId("test@yahoo.net")
				.password("1234")
				.nickname("테스트")
				.phoneNumber("010-1234-5678")
				.build();
		int retValue = memberService.signup(memberReqDto);
		Assertions.assertThat(retValue).isEqualTo(1);
	}

	@Test
	@Transactional
	void login() {
		MemberLoginReqDto memberLoginReqDto = MemberLoginReqDto.builder()
				.memberId(testMemberId)
				.password(testPassword)
				.build();
		MemberLoginResDto memberLoginResDto = memberService.login(memberLoginReqDto);
		log.info(memberLoginResDto.toString());
		Assertions.assertThat(memberLoginResDto.getMemberNo()).isEqualTo(testMemberNo);
	}

	@Test
	@Transactional
	void idCheckTest() {
		int resValue = memberService.idCheck(testMemberId);
		Assertions.assertThat(resValue).isEqualTo(1);
	}

	@Test
	@Transactional
	void memberDetailTest() {
		MemberDetailResDto resValue = memberService.memberDetail(testMemberNo);
		Assertions.assertThat(resValue).toString();
	}

	@Test
	@Transactional
	void memberModifyTest() {
		MemberModifyReqDto memberModifyReqDto = MemberModifyReqDto.builder()
				.memberNo(testMemberNo)
				.password("1357")
				.nickname("닉네임")
				.phoneNumber("111122222")
				.build();
		int resValue = memberService.memberModify(memberModifyReqDto);
		Assertions.assertThat(resValue).isEqualTo(1);
	}

	@Test
	@Transactional
	void memberSignOutTest() {
		int resValue = memberService.memberSignOut(testMemberNo);
		Assertions.assertThat(resValue).isEqualTo(1);
	}

}
