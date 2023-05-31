package com.viktor.recipebackend.structures;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {
    private UUID id;
    private UUID idUser;
    private UUID idRecipe;
}
