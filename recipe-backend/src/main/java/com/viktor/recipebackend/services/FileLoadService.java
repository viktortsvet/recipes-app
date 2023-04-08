package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.FileEntity;
import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

@Service
public class FileLoadService {
    private final FileEntityService fileEntityService;

    @Autowired
    public FileLoadService(FileEntityService fileEntityService) {
        this.fileEntityService = fileEntityService;
    }

    public void handleFileUpload(@NonNull MultipartFile file,
                                 @NonNull String fileName,
                                 @NonNull String filePath,
                                 @Nullable String description,
                                 @Nullable User user,
                                 @Nullable Recipe recipe) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileName));
                stream.write(bytes);
                stream.close();
                fileEntityService.saveFileEntity(FileEntity.builder()
                        .name(fileName).filePath(filePath).description(description)
                        .user(user).recipe(recipe).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}