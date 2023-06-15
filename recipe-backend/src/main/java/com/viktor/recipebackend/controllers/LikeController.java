package com.viktor.recipebackend.controllers;

import com.viktor.recipebackend.services.LikeService;
import com.viktor.recipebackend.structures.LikeDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("save-like")
    public ResponseEntity<Void> saveLike(@RequestBody LikeDto likeDto) {
        if (likeDto.getIdRecipe() == null && likeDto.getIdUser() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                likeService.saveLike(likeDto);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (EntityNotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @GetMapping("get-likes-length")
    public ResponseEntity<Long> getLikesLength(@RequestParam(value = "idRecipe") UUID idRecipe) {
        return new ResponseEntity<>(likeService.getRecipeLikesQuantity(idRecipe), HttpStatus.OK);
    }
}
