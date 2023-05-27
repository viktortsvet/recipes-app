package com.viktor.recipebackend.services;

import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.entities.User;
import com.viktor.recipebackend.repositories.UserRepository;
import com.viktor.recipebackend.structures.UserDTO;
import com.viktor.recipebackend.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final QueryService queryService;

    @Autowired
    public UserService(UserRepository userRepository,
                       QueryService queryService) {
        this.userRepository = userRepository;
        this.queryService = queryService;
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