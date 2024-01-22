package com.ehours.goldenchild.file.controller;

import com.ehours.goldenchild.common.ResponseResource;
import com.ehours.goldenchild.file.dto.FileResponseDto;
import com.ehours.goldenchild.file.service.FileService;
import com.ehours.goldenchild.file.service.FileUtils;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @PostMapping("/upload/{memberId}")
    public ResponseEntity<Map<String, Object>> saveAllFiles(List<MultipartFile> files, @PathVariable int memberId) {
        int retValue = fileService.saveAllFiles(files, memberId);
        if (retValue != 0) return ResponseResource.handleSuccess(retValue, "파일 업로드 성공!");
        else return ResponseResource.handleError("파일 업로드 실패..");
    }

    @GetMapping("/detail/all/{fileListId}")
    public ResponseEntity<Map<String, Object>> getFilesByFileListId(@PathVariable int fileListId) {
        List<FileResponseDto> fileResponseDtoList = fileService.findFilesByFileListId(fileListId);
        if (fileResponseDtoList != null) return ResponseResource.handleSuccess(fileResponseDtoList, "파일 리스트 가져오기 성공!");
        else return ResponseResource.handleError("파일 리스트 가져오기 실패..");
    }

    @GetMapping("/detail/{fileId}")
    public ResponseEntity<Map<String, Object>> getFileByFileId(@PathVariable int fileId) {
        FileResponseDto fileResponseDto = fileService.findFileByFileId(fileId);
        if (fileResponseDto != null) return ResponseResource.handleSuccess(fileResponseDto, "개별 파일 가져오기 성공!");
        else return ResponseResource.handleError("개별 파일 가져오기 실패..");
    }

    @PutMapping("/delete/all/{fileListId}")
    public ResponseEntity<Map<String, Object>> deleteAllFilesByFileListId(@PathVariable int fileListId) {
        int retValue = fileService.deleteFilesByFileListId(fileListId);
        if (retValue != 0) return ResponseResource.handleSuccess(retValue, "파일 전체 삭제 완료!");
        else return ResponseResource.handleError("파일 전체 삭제 실패..");
    }

    @PutMapping("/delete/{fileId}")
    public ResponseEntity<Map<String, Object>> deleteFileByFileId(@PathVariable int fileId) {
        int retValue = fileService.deleteFileByFileId(fileId);
        if (retValue != 0) return ResponseResource.handleSuccess(retValue, "파일 전체 삭제 완료!");
        else return ResponseResource.handleError("파일 전체 삭제 실패..");
    }
}
