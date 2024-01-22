package com.ehours.goldenchild.file.service;

import com.ehours.goldenchild.file.dto.FileListIdDto;
import com.ehours.goldenchild.file.dto.FileRequestDto;
import com.ehours.goldenchild.file.dto.FileResponseDto;
import com.ehours.goldenchild.file.mapper.FileMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService{
    private final FileMapper fileMapper;
    private final FileUtils fileUtils;
    @Transactional
    @Override
    public int saveAllFiles(List<MultipartFile> files, int memberId) { // All or Nothing
        if (files.isEmpty()) return 0;
        FileListIdDto fileListIdDto = new FileListIdDto();
        fileMapper.makeFileList(fileListIdDto);

        List<FileRequestDto> fileRequestDtoList = fileUtils.uploadFiles(files);
        for (FileRequestDto fileRequestDto : fileRequestDtoList) {
            fileRequestDto.setMemberId(memberId);
            fileRequestDto.setFileListId(fileListIdDto.getFileListId());
        }
        int retValue = fileMapper.saveAllFiles(fileRequestDtoList);
        if (files.size() == retValue) return fileListIdDto.getFileListId();
        else return 0;
    }

    @Override
    public List<FileResponseDto> findFilesByFileListId(int fileListId) {
        return fileMapper.findFilesByFileListId(fileListId);
    }

    @Override
    public int deleteFilesByFileListId(int fileListId) {
        return fileMapper.deleteFilesByFileListId(fileListId);
    }

    @Override
    public FileResponseDto findFileByFileId(int fileId) {
        return fileMapper.findFileByFileId(fileId);
    }

    @Override
    public int deleteFileByFileId(int fileId) {
        return fileMapper.deleteFileByFileId(fileId);
    }
}
