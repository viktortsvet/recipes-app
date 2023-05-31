package com.viktor.recipebackend.repositories;

import com.viktor.recipebackend.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikesRepository extends JpaRepository<Like, UUID> {
}
