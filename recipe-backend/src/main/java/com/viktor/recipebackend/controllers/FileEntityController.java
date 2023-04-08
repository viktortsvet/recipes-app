package com.viktor.recipebackend.controllers;

import com.viktor.recipebackend.services.FileEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("file")
public class FileEntityController {
    private final FileEntityService fileEntityService;

    @Autowired
    public FileEntityController(FileEntityService fileEntityService) {
        this.fileEntityService = fileEntityService;
    }
}