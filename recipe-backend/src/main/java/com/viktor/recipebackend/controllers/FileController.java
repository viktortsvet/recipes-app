package com.viktor.recipebackend.controllers;

import com.viktor.recipebackend.services.FileGenerateService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("file")
@RequiredArgsConstructor
public class FileController {

    private final FileGenerateService fileGenerateService;

    @GetMapping("file-test")
    public ResponseEntity<Void> loadFile(HttpServletResponse response) throws Exception {
        fileGenerateService.generateFile(response);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
