package com.ehours.goldenchild;

import com.ehours.goldenchild.member.dto.MemberDetailResDto;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberModifyReqDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import com.ehours.goldenchild.sprint.service.SprintService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GoldenchildApplicationTests {
	@Autowired
	private MemberService memberService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SprintService sprintService;


	private MemberLoginResDto login;
	private String id = "test54321@kakao.com";
	private String pw = "password1234";
	@BeforeAll
	public void setUp() {
		MemberSignUpReqDto memberSignUpReqDto = MemberSignUpReqDto.builder()
				.memberId(id)
				.password(pw)
				.nickname("카카오")
				.phoneNumber("010-1234-5678")
				.build();
		memberService.signup(memberSignUpReqDto);
		MemberLoginReqDto memberLoginReqDto = MemberLoginReqDto.builder()
				.memberId(id)
				.password(pw)
				.build();
		login = memberService.login(memberLoginReqDto);
	}

	@AfterAll
	public void deleteSetup() {
		jdbcTemplate.update("DELETE FROM member WHERE no = ?", login.getMemberNo());
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
				.memberId(id)
				.password(pw)
				.build();
		MemberLoginResDto memberLoginResDto = memberService.login(memberLoginReqDto);
		log.info(memberLoginResDto.toString());
		Assertions.assertThat(memberLoginResDto.getMemberNo()).isEqualTo(login.getMemberNo());
	}

	@Test
	@Transactional
	void idCheckTest() {
		int resValue = memberService.idCheck(id);
		Assertions.assertThat(resValue).isEqualTo(1);
	}

	@Test
	@Transactional
	void memberDetailTest() {
		MemberDetailResDto resValue = memberService.memberDetail(login.getMemberNo());
		Assertions.assertThat(resValue.getMemberId()).isEqualTo(id);
	}

	@Test
	@Transactional
	void memberModifyTest() {
		MemberModifyReqDto memberModifyReqDto = MemberModifyReqDto.builder()
				.memberNo(login.getMemberNo())
				.password(pw)
				.newPassword("13232413")
				.nickname("닉네임")
				.phoneNumber("111122222")
				.build();
		int resValue = memberService.memberModify(memberModifyReqDto);
		Assertions.assertThat(resValue).isEqualTo(1);
	}

	@Test
	@Transactional
	void memberSignOutTest() {
		int resValue = memberService.memberSignOut(login.getMemberNo());
		Assertions.assertThat(resValue).isEqualTo(1);
	}
}
