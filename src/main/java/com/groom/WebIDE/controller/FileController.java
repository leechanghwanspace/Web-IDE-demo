package com.groom.WebIDE.controller;

import com.groom.WebIDE.entity.FileEntity;
import com.groom.WebIDE.entity.Folder;
import com.groom.WebIDE.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@Tag(name = "File API", description = "파일 관리와 관련된 API")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    @Operation(summary = "파일 생성", description = "새로운 파일을 생성하고 폴더와 연결합니다.")
    public FileEntity createFile(@RequestParam String name,
                                 @RequestParam String content,
                                 @RequestParam Long folderId) {
        return fileService.createFile(name, content, folderId);
    }

    @GetMapping("/{folderId}")
    @Operation(summary = "폴더 내 파일 조회", description = "특정 폴더에 속한 파일 목록을 조회합니다.")
    public List<FileEntity> getFilesByFolder(@PathVariable Long folderId) {
        return fileService.getFilesByFolder(folderId);
    }

    @PutMapping("/{fileId}")
    @Operation(summary = "파일 이름 수정", description = "파일의 이름을 수정합니다.")
    public FileEntity updateFileName(@PathVariable Long fileId, @RequestParam String newName) {
        return fileService.updateFileName(fileId, newName);
    }

    @GetMapping
    @Operation(summary = "파일 및 폴더 계층 조회", description = "폴더와 해당 폴더 내 파일들의 계층 구조를 반환합니다.")
    public List<Folder> getFolderHierarchy() {
        return fileService.getFolderHierarchy();
    }

    @DeleteMapping("/{fileId}")
    @Operation(summary = "파일 삭제", description = "특정 파일을 삭제합니다.")
    public void deleteFile(@PathVariable Long fileId) {
        fileService.deleteFile(fileId);
    }
}
