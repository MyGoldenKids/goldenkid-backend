package com.ehours.goldenchild.file.service;

import com.ehours.goldenchild.file.dto.FileRequestDto;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {
//    @Value("#{app.uploadPath}")
    private String uploadPath = "C:/develop/upload-files";

    public List<FileRequestDto> uploadFiles(List<MultipartFile> multipartFiles) {
        List<FileRequestDto> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.isEmpty()) continue;
            files.add(upLoadFile(multipartFile));
        }
        return files;
    }

    public FileRequestDto upLoadFile(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) return null;

        String saveFileName = generateSaveFileName(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String uploadPath = getUploadPath(today) + File.separator + saveFileName;
        File uploadFile = new File(uploadPath);

        try {
            multipartFile.transferTo(uploadFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileRequestDto.builder()
                .fileOriginalName(multipartFile.getOriginalFilename())
                .fileSaveName(saveFileName)
                .fileSize(multipartFile.getSize())
                .fileType(multipartFile.getContentType())
                .build(); // fileType은 Util 호출 한 곳에서 따로 넣어줘야 함.
    }

    private String generateSaveFileName(String fileName) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(fileName);
        return uuid + "." + extension;
    }

    private String getUploadPath(String today) {
        return makeDirectory(uploadPath + today);
    }
    private String makeDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getPath();
    }
}
