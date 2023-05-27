package com.viktor.recipebackend.repositories;

import com.viktor.recipebackend.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
}
