package com.viktor.recipebackend.repositories;

import com.viktor.recipebackend.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileEntityRepository extends JpaRepository<FileEntity, UUID> {
}