package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.Chat;
import com.viktor.recipebackend.repositories.ChatRepository;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void createChat(@Nonnull Chat chat) {
        chatRepository.save(chat);
    }

    public Optional<Chat> getChatById(@Nonnull UUID idChat) {
        return chatRepository.existsById(idChat) ? chatRepository.findById(idChat) : Optional.empty();
    }
}
