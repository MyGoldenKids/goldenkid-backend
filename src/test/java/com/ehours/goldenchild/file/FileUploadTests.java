package com.ehours.goldenchild.file;

import com.ehours.goldenchild.file.dto.FileListIdDto;
import com.ehours.goldenchild.file.service.FileService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class FileUploadTests {
    @Autowired
    FileService fileService;

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
        int fileListId = fileService.saveAllFiles(list, 1);
        Assertions.assertThat(fileService.findFilesByFileListId(fileListId).size()).isEqualTo(2);
    }
}
