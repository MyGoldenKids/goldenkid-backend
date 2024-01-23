package com.ehours.goldenchild.file.service;

import com.ehours.goldenchild.file.dto.FileRequestDto;
import com.ehours.goldenchild.file.dto.FileResponseDto;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {
    @Value("${app.uploadPath}")
    private String uploadPath;

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

    public int deleteFiles(final List<FileResponseDto> files) {
        if (files.isEmpty()) {
            return 0;
        }
        int retValue = 0;
        for (FileResponseDto file : files) {
            String uploadedDate = file.getFileCreatedDate()
                    .toLocalDate()
                    .format(DateTimeFormatter.ofPattern("yyMMdd"));
            deleteFile(uploadedDate, file.getFileSaveName());
            retValue++;
        }
        return retValue;
    }

    private void deleteFile(final String addPath, final String filename) {
        String filePath = Paths.get(uploadPath, addPath, filename).toString();
        deleteFile(filePath);
    }

    private void deleteFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public Resource readFileAsResource(FileResponseDto file) {
        String uploadDate = file.getFileCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String fileName = file.getFileSaveName();
        Path filePath = Paths.get(uploadPath, uploadDate, fileName);
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isFile()) {
                throw new RuntimeException("file not found : " + filePath.toString());
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException("file not found : " + filePath.toString());
        }
    }
    private String generateSaveFileName(String fileName) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(fileName);
        return uuid + "." + extension;
    }

    private String getUploadPath(String today) {
        return makeDirectory(uploadPath + File.separator + today);
    }
    private String makeDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getPath();
    }

}
