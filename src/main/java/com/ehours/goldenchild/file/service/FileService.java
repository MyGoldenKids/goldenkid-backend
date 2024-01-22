package com.ehours.goldenchild.file.service;

import com.ehours.goldenchild.file.dto.FileRequestDto;
import com.ehours.goldenchild.file.dto.FileResponseDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public int saveAllFiles(List<MultipartFile> files, int memberId);

    public List<FileResponseDto> findFilesByFileListId(int fileListId);
    public int deleteFilesByFileListId(int fileListId);

    public FileResponseDto findFileByFileId(int fileId);
    public int deleteFileByFileId(int fileId);
}
