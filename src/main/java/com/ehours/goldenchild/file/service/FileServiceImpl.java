package com.ehours.goldenchild.file.service;

import com.ehours.goldenchild.file.dto.FileListIdDto;
import com.ehours.goldenchild.file.dto.FileRequestDto;
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
        System.out.println(fileListIdDto.getFileListId());
        List<FileRequestDto> fileRequestDtoList = fileUtils.uploadFiles(files);
        for (FileRequestDto fileRequestDto : fileRequestDtoList) {
            fileRequestDto.setFileListId(fileListIdDto.getFileListId());
            fileRequestDto.setMemberId(memberId);
        }
        return fileMapper.saveAllFiles(fileRequestDtoList);
    }
}
