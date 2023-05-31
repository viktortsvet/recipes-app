package com.viktor.recipebackend.controllers;

import com.viktor.recipebackend.services.LikeService;
import com.viktor.recipebackend.structures.LikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("save")
    public ResponseEntity<Void> saveLike(@RequestBody LikeDto likeDto) {
        if (likeDto.getIdRecipe() == null && likeDto.getIdUser() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            likeService.saveLike(likeDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
