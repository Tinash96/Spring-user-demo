package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Repository
public class FakeRepo implements FakeRepoInterface {

    private Map<UUID, User> userDatabase = new HashMap<>();  // In-memory map to store users

    @Override
    public User insertUser(String name, String surname) {
        UUID id = UUID.randomUUID();
        User user = new User(id, name, surname);
        userDatabase.put(id, user);  // Store the user in the map
        return user;
    }

    @Override
    public User findUserById(UUID id) {
        return userDatabase.get(id);  // Retrieve user by UUID
    }

    @Override
    public boolean deleteUser(UUID id) {
        return userDatabase.remove(id) != null;  // Delete user by UUID
    }
}
