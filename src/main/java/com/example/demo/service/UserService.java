package com.example.demo.service;

import com.example.demo.model.User;

import java.util.UUID;

public interface UserService {
    User addUser(String name, String surname);
    User getUser(UUID id);
    boolean removeUser(UUID id);
}
