package com.viktor.recipebackend.services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class FileGenerateService {

    public void generateFile(HttpServletResponse response) throws IOException {
        File file = File.createTempFile("testFileTest", ".txt");
        byte[] bytes = "hello there!".getBytes();
        Files.write(file.toPath(), bytes);
        response.setHeader("Content-Disposition", "attachment;filename=teeeeeesst.txt");
        Files.delete(file.toPath());
    }
}
