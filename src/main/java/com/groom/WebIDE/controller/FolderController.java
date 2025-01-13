package com.groom.WebIDE.controller;

import com.groom.WebIDE.entity.Folder;
import com.groom.WebIDE.service.FolderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/folders")
@Tag(name = "Folder API", description = "폴더 관리와 관련된 API")
public class FolderController {
    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping
    @Operation(summary = "폴더 생성", description = "새로운 폴더를 생성합니다.")
    public Folder createFolder(@RequestParam String name) {
        return folderService.createFolder(name);
    }

    @GetMapping
    @Operation(summary = "폴더 목록 조회", description = "모든 폴더를 조회합니다.")
    public List<Folder> getAllFolders() {
        return folderService.getAllFolders();
    }

    @DeleteMapping("/{folderId}")
    @Operation(summary = "폴더 삭제", description = "폴더와 해당 폴더 내 모든 파일을 삭제합니다.")
    public void deleteFolder(@PathVariable Long folderId) {
        folderService.deleteFolder(folderId);
    }
}
