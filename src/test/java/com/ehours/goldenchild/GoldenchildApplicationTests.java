package com.ehours.goldenchild;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import org.assertj.core.api.Assertions;
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
class GoldenchildApplicationTests {

	@Autowired
	MemberService memberService;
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
	void idCheckTest() {
		String resValue = memberService.idCheck("test@naver.com");
		Assertions.assertThat(resValue).isEqualTo("test@naver.com");
	}

	@Test
	@Transactional
	void memberDetailTest() {
		MemberDetailResDto resValue = memberService.memberDetail("test@naver.com");
		Assertions.assertThat(resValue).toString();
	}

	@Test
	@Transactional
	void memberModifyTest() {
		MemberModifyReqDto memberModifyReqDto = MemberModifyReqDto.builder()
				.memberId("test@naver.com")
				.password("1234")
				.nickname("닉네임")
				.phoneNumber("111122222")
				.build();
		int resValue = memberService.memberModify(memberModifyReqDto);
		Assertions.assertThat(resValue).isEqualTo(1);
	}

	@Test
	@Transactional
	void memberSignOutTest() {
		int resValue = memberService.memberSignOut("test@naver.com");
		Assertions.assertThat(resValue).isEqualTo(1);
	}

}
