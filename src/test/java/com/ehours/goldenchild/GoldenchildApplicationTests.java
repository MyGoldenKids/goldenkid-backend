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

	private static final String testMemberId = "test@naver.com";
	private static final String testPassword = "1234";
	private static final String testNickname = "테스트";
	private static final String testPhoneNumber = "010-1234-5678";

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	void signupTest() {
		MemberSignUpReqDto memberReqDto = MemberSignUpReqDto.builder()
				.memberId("test@gmail.com1")
				.password("test")
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
		Assertions.assertThat(memberLoginResDto.getMemberId()).isEqualTo(29);
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
		MemberDetailResDto resValue = memberService.memberDetail(testMemberId);
		Assertions.assertThat(resValue).toString();
	}

	@Test
	@Transactional
	void memberModifyTest() {
		MemberModifyReqDto memberModifyReqDto = MemberModifyReqDto.builder()
				.memberId(testMemberId)
				.password(testPassword)
				.nickname("닉네임")
				.phoneNumber("111122222")
				.build();
		int resValue = memberService.memberModify(memberModifyReqDto);
		Assertions.assertThat(resValue).isEqualTo(1);
	}

	@Test
	@Transactional
	void memberSignOutTest() {
		int resValue = memberService.memberSignOut(testMemberId);
		Assertions.assertThat(resValue).isEqualTo(1);
	}

}
