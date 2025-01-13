package com.groom.WebIDE.service;

import com.groom.WebIDE.entity.Folder;
import com.groom.WebIDE.repository.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {
    private final FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    /**
     * 폴더 생성
     * @param name 폴더 이름
     * @return 생성된 폴더 엔티티
     */
    public Folder createFolder(String name) {
        Folder folder = new Folder();
        folder.setName(name);
        return folderRepository.save(folder);
    }

    /**
     * 모든 폴더 조회
     * @return 폴더 목록
     */
    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    /**
     * 폴더 삭제
     * @param folderId 삭제할 폴더의 ID
     */
    public void deleteFolder(Long folderId) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new IllegalArgumentException("Folder not found"));

        folderRepository.delete(folder);
    }
}
