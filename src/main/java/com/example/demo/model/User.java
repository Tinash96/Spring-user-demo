package com.example.demo.model;

import java.util.UUID;

public class User {
    private UUID id;  // Change the type to UUID
    private String name;
    private String surname;

    public User(UUID id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
