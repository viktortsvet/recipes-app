package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.FileEntity;
import com.viktor.recipebackend.repositories.FileEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FileEntityService {
    private final FileEntityRepository fileEntityRepository;

    @Autowired
    public FileEntityService(FileEntityRepository fileEntityRepository) {
        this.fileEntityRepository = fileEntityRepository;
    }

    public void saveFileEntity(FileEntity fileEntity) {
        fileEntityRepository.save(fileEntity);
    }

    public FileEntity getFileEntity(@NonNull UUID idFileEntity) {
        return fileEntityRepository.findById(idFileEntity).orElseThrow();
    }
}