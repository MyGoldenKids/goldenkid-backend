package com.ehours.goldenchild.file.mapper;

import com.ehours.goldenchild.file.dto.FileListIdDto;
import com.ehours.goldenchild.file.dto.FileRequestDto;
import com.ehours.goldenchild.file.dto.FileResponseDto;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FileMapper {

    // 파일 리스트 조회 및 삭제
    @Select("select " +
            "file_list_id " +
            "from file_list " +
            "where file_list_id = #{fileListId}"
    )
    Integer findFileListByFileListId(int fileListId);

    @Delete("delete from file_list where file_list_id=#{fileListId}")
    int deleteFileListByFileListId(int fileListId);

    // 파일 리스트 기반 파일 조회 및 삭제
    @Results(id = "fileMap", value = {
            @Result(column = "file_id", property = "fileId"),
            @Result(column = "member_id", property = "memberId"),
            @Result(column = "file_list_id", property = "fileListId"),
            @Result(column = "file_original_name", property = "fileOriginalName"),
            @Result(column = "file_save_name", property = "fileSaveName"),
            @Result(column = "file_size", property = "fileSize"),
            @Result(column = "file_type", property = "fileType"),
            @Result(column = "file_created_date", property = "fileCreatedDate")
        }
    )
    @Select("select " +
            "file_id, member_id, file_list_id, file_original_name, file_save_name, file_size, file_type, file_created_date " +
            "from file " +
            "where file_list_id = #{fileListId} and file_status=1 " +
            "order by file_id")
    List<FileResponseDto> findFilesByFileListId(int fileListId);

    @Delete("delete from file where file_list_id=#{fileListId}")
    int deleteFilesByFileListId(int fileListId);

    // 파일 아이디 기반 조회 및 삭제
    @ResultMap("fileMap")
    @Select("select " +
            "file_id, member_id, file_list_id, file_original_name, file_save_name, file_size, file_type, file_created_date " +
            "from file " +
            "where file_id = #{fileId} " +
            "order by file_id")
    FileResponseDto findFileByFileId(int fileId);

    @Update("update file set file_status = 0 where file_id = #{fileId}")
    int deleteFileByFileId(int fileId);

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
