package com.groom.WebIDE.service;

import com.groom.WebIDE.entity.FileEntity;
import com.groom.WebIDE.entity.Folder;
import com.groom.WebIDE.repository.FileRepository;
import com.groom.WebIDE.repository.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;

    public FileService(FileRepository fileRepository, FolderRepository folderRepository) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
    }

    /**
     * 파일 생성
     * @param name 파일 이름
     * @param content 파일 내용
     * @param folderId 파일이 속할 폴더의 ID
     * @return 생성된 파일 엔티티
     */
    public FileEntity createFile(String name, String content, Long folderId) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new IllegalArgumentException("Folder not found"));

        FileEntity file = new FileEntity();
        file.setName(name);
        file.setContent(content);
        file.setFolder(folder);

        return fileRepository.save(file);
    }

    /**
     * 특정 폴더에 속한 파일 조회
     * @param folderId 폴더 ID
     * @return 해당 폴더의 파일 목록
     */
    public List<FileEntity> getFilesByFolder(Long folderId) {
        return fileRepository.findByFolderId(folderId);
    }

    /**
     * 파일 삭제
     * @param fileId 삭제할 파일의 ID
     */
    public void deleteFile(Long fileId) {
        fileRepository.deleteById(fileId);
    }

    /**
     * 파일 이름 수정
     * @param fileId 수정할 파일의 ID
     * @param newName 새로운 파일 이름
     * @return 수정된 파일 엔티티
     */
    public FileEntity updateFileName(Long fileId, String newName) {
        FileEntity file = fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("File not found"));

        file.setName(newName);
        return fileRepository.save(file);
    }

    /**
     * 폴더와 파일 계층 구조 조회
     * @return 모든 폴더와 해당 폴더의 파일 목록
     */
    public List<Folder> getFolderHierarchy() {
        return folderRepository.findAll();
    }
}
