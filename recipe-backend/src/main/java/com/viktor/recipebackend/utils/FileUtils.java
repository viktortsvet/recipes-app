package com.viktor.recipebackend.utils;

import jakarta.annotation.Nonnull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static void createDirectory(@Nonnull String directory) {
        Path path = Path.of(directory);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        System.out.println("Directory already exists!");
    }
}
