package com.ehours.goldenchild.file.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileResponseDto {
    private int fileId;
    private int memberId;
    private int fileListId;
    private String fileOriginalName;
    private String fileSaveName;
    private long fileSize;
    private String fileType;
    private LocalDateTime fileCreatedDate;
}
