package com.groom.WebIDE.repository;

import com.groom.WebIDE.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}