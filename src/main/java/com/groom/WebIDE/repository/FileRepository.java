package com.groom.WebIDE.repository;

import com.groom.WebIDE.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    // 특정 폴더에 속한 파일 목록 조회
    List<FileEntity> findByFolderId(Long folderId);
}