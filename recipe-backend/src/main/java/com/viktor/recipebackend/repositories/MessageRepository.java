package com.viktor.recipebackend.repositories;

import com.viktor.recipebackend.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

}