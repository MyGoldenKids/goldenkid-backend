package com.ehours.goldenchild.file;

import com.ehours.goldenchild.file.dto.FileListIdDto;
import com.ehours.goldenchild.file.service.FileService;
import com.ehours.goldenchild.member.dto.MemberLoginReqDto;
import com.ehours.goldenchild.member.dto.MemberLoginResDto;
import com.ehours.goldenchild.member.dto.MemberSignUpReqDto;
import com.ehours.goldenchild.member.service.MemberService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileUploadTests {
    @Autowired
    FileService fileService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    MemberService memberService;
    private MemberLoginResDto login;

    @BeforeAll
    public void setUp() {
        String id = "test54321@kakao.com";
        String pw = "password1234";
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
    void fileUploadTest() throws IOException {
        FileListIdDto fileListIdDto;
        MultipartFile multipartFile1 = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "test file".getBytes(StandardCharsets.UTF_8)
        );
        MultipartFile multipartFile2 = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "test file".getBytes(StandardCharsets.UTF_8)
        );
        List<MultipartFile> list = new ArrayList<>();
        list.add(multipartFile1);
        list.add(multipartFile2);
        int fileListId = fileService.saveAllFiles(list, login.getMemberNo());
        Assertions.assertThat(fileService.findFilesByFileListId(fileListId).size()).isEqualTo(2);
    }
}
