package com.ehours.goldenchild;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/*
public class MemberDto {
    private int no;
    private int fileId;
    private String memberId;
    private String password;
    private String nickname;
    private boolean memberStatus;
    private String phoneNumber;
    private String joinedAt;
}
 */
@SpringBootTest
@Slf4j
class GoldenchildApplicationTests {

	@Autowired
	MemberService memberService;

	private static String testMemberId = "test@naver.com";
	private static String testPassword = "1234";
	private static String testNickname = "테스트";
	private static String testPhoneNumber = "010-1234-5678";

	@Test
	void contextLoads() {
	}


	@Test
	@Transactional
	void signup(){
		MemberSignUpReqDto memberReqDto = MemberSignUpReqDto.builder()
				.memberId(testMemberId)
				.password(testPassword)
				.nickname(testNickname)
				.phoneNumber(testPhoneNumber)
				.build();

		int retValue = memberService.signup(memberReqDto);
		Assertions.assertThat(retValue).isEqualTo(1);
	}

	@Test
	@Transactional
	void login() {
		MemberSignUpReqDto memberReqDto = MemberSignUpReqDto.builder()
			.memberId(testMemberId)
			.password(testPassword)
			.nickname(testNickname)
			.phoneNumber(testPhoneNumber)
			.build();

		int retValue = memberService.signup(memberReqDto);

		MemberLoginReqDto memberLoginReqDto = MemberLoginReqDto.builder()
				.memberId(testMemberId)
				.password(testPassword)
				.build();
		MemberLoginResDto memberLoginResDto = memberService.login(memberLoginReqDto);
		log.info(memberLoginResDto.toString());
		Assertions.assertThat(memberLoginResDto.getMemberId()).isEqualTo(memberLoginReqDto.getMemberId());
	}

	@Test
	@Transactional
	void idCheckTest() {
		int resValue = memberService.idCheck("test@gmail.com");
		Assertions.assertThat(resValue).isEqualTo(1);
	}

	@Test
	@Transactional
	void memberDetailTest() {
		MemberSignUpReqDto memberReqDto = MemberSignUpReqDto.builder()
				.memberId(testMemberId)
				.password(testPassword)
				.nickname(testNickname)
				.phoneNumber(testPhoneNumber)
				.build();

		int retValue = memberService.signup(memberReqDto);

		MemberDetailResDto resValue = memberService.memberDetail(testMemberId);
		Assertions.assertThat(resValue).toString();
	}

	@Test
	@Transactional
	void memberModifyTest() {
		MemberSignUpReqDto memberReqDto = MemberSignUpReqDto.builder()
				.memberId(testMemberId)
				.password(testPassword)
				.nickname(testNickname)
				.phoneNumber(testPhoneNumber)
				.build();

		int retValue = memberService.signup(memberReqDto);

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
		MemberSignUpReqDto memberReqDto = MemberSignUpReqDto.builder()
				.memberId(testMemberId)
				.password(testPassword)
				.nickname(testNickname)
				.phoneNumber(testPhoneNumber)
				.build();

		int retValue = memberService.signup(memberReqDto);

		int resValue = memberService.memberSignOut(testMemberId);
		Assertions.assertThat(resValue).isEqualTo(1);
	}

}
