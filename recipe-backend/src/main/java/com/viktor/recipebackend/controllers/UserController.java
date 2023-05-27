package com.viktor.recipebackend.controllers;

import com.viktor.recipebackend.entities.Chat;
import com.viktor.recipebackend.entities.Recipe;
import com.viktor.recipebackend.entities.User;
import com.viktor.recipebackend.services.ChatService;
import com.viktor.recipebackend.services.UserService;
import com.viktor.recipebackend.structures.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final ChatService chatService;

    @Autowired
    public UserController(UserService userService, ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
    }

    @GetMapping("get-all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("doChat")
    public ResponseEntity<?> doChat(@RequestParam(value = "idChat") UUID idChat,
                                    @RequestParam(value = "idUser") UUID idUser) {
        User user = userService.getUserById(idUser).orElseThrow();
        Chat chat = chatService.getChatById(idChat).orElseThrow();
        user.addChat(chat);
        userService.addOrUpdateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getUserById")
    public ResponseEntity<Optional<User>> getUserById(@RequestParam(value = "idUser") UUID idUser) {
        return new ResponseEntity<>(userService.getUserById(idUser), HttpStatus.OK);
    }

    @PostMapping(value = "addOrUpdate")
    public ResponseEntity<?> addOrUpdateUser(@RequestBody User user) {
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}