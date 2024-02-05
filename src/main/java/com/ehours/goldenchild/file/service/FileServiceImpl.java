package com.ehours.goldenchild.file.service;

import com.ehours.goldenchild.file.dto.FileListIdDto;
import com.ehours.goldenchild.file.dto.FileRequestDto;
import com.ehours.goldenchild.file.dto.FileResponseDto;
import com.ehours.goldenchild.file.mapper.FileMapper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
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
    public int saveFilesByFileListId(List<MultipartFile> files, int fileListId, int memberId) {
        if (files.isEmpty()) return 0;

        List<FileRequestDto> fileRequestDtoList = fileUtils.uploadFiles(files);
        for (FileRequestDto fileRequestDto : fileRequestDtoList) {
            fileRequestDto.setFileListId(fileListId);
            fileRequestDto.setMemberId(memberId);
        }
        int retValue = fileMapper.saveAllFiles(fileRequestDtoList);
        if (files.size() == retValue) return retValue;
        else return 0;
    }

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
        int localRetValue = fileUtils.deleteFiles(fileMapper.findFilesByFileListId(fileListId));
        int dbRetValue = fileMapper.deleteFilesByFileListId(fileListId);
        fileMapper.deleteFileListByFileListId(fileListId);
        if (localRetValue == dbRetValue) return dbRetValue;
        else return 0;
    }

    @Override
    public FileResponseDto findFileByFileId(int fileId) {
        return fileMapper.findFileByFileId(fileId);
    }

    @Override
    public int deleteFileByFileId(int fileId) {
        List<FileResponseDto> fileResponseDtoList = new ArrayList<>();
        FileResponseDto fileResponseDto = fileMapper.findFileByFileId(fileId);
        fileResponseDtoList.add(fileResponseDto);
        int localRetValue = fileUtils.deleteFiles(fileResponseDtoList);
        int dbRetValue = fileMapper.deleteFileByFileId(fileId);
        if (localRetValue == dbRetValue) return dbRetValue;
        else return 0;
    }

    @Override
    public Resource downloadFileByFileId(int fileId) {
        FileResponseDto file = fileMapper.findFileByFileId(fileId);
        Resource resource = fileUtils.readFileAsResource(file);
        try {
            String fileName = URLEncoder.encode(file.getFileOriginalName(), "UTF-8");
            return resource;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("fileName encoding 실패.." + file.getFileOriginalName());
        }
    }
}
