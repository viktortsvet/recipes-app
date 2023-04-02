package com.viktor.recipebackend.structures;

import java.util.UUID;

public class UserDTO {
    private String name;
    private String lastname;
    private UUID id;

    public UserDTO() {}

    public UserDTO(UUID id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public UserDTO(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }
}