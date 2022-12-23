package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.User;
import com.viktor.recipebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(UUID idUser) {
        return userRepository.existsById(idUser) ? userRepository.findById(idUser) : Optional.empty();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addOrUpdateUser(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            userRepository.save(user);
        } else {
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setLastname(user.getLastname());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            newUser.setRole(user.getRole());
            userRepository.save(newUser);
        }
    }
}