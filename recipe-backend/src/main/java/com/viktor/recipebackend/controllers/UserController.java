package com.viktor.recipebackend.controllers;

import com.viktor.recipebackend.entities.User;
import com.viktor.recipebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("get-all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("getByTheirIds")
    public ResponseEntity<List<User>> getUsersByTheirIds(@RequestBody List<UUID> ids) {
        List<String> dataIds = new ArrayList<>();
        for (UUID id : ids) {
            dataIds.add(id.toString());
        }
        return new ResponseEntity<>(userService.getUsersByTheirIds(dataIds), HttpStatus.OK);
    }

    @PostMapping(value = "addOrUpdate")
    public ResponseEntity<?> addOrUpdateUser(@RequestBody User user) {
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}