package com.ehours.goldenchild.file.mapper;

import com.ehours.goldenchild.file.dto.FileListIdDto;
import com.ehours.goldenchild.file.dto.FileRequestDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface FileMapper {
//    @Results(id = "fileMap", value = {
//            @Result(column = "file_id", property = "fileId"),
//            @Result(column = "member_id", property = "memberId"),
//            @Result(column = "file_list_id", property = "fileListId"),
//            @Result(column = "file_original_name", property = "fileOriginalName"),
//            @Result(column = "file_save_name", property = "fileSaveName"),
//            @Result(column = "file_size", property = "fileSize")
//        }
//    )
    @Insert({
            "<script>",
            "insert into file (member_id, file_list_id, file_original_name, file_save_name, file_size, file_type)",
            "values",
            "<foreach collection='list' item='item' separator=','>",
            "(#{item.memberId}, #{item.fileListId}, #{item.fileOriginalName}, #{item.fileSaveName}, #{item.fileSize}, #{item.fileType})",
            "</foreach>",
            "</script>"
    })
    int saveAllFiles(List<FileRequestDto> files);

    @Insert("insert into file_list (file_list_id) values (default)")
    @Options(useGeneratedKeys = true, keyProperty = "fileListId")
    int makeFileList(FileListIdDto fileListIdDto);
}
