package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class FakeRepo implements FakeRepoInterface {

    private Map<UUID, User> userDatabase = new HashMap<>();

    @Override
    public User insertUser(String name, String surname) {
        UUID id = UUID.randomUUID();  // Generate UUID automatically for the user
        User user = new User(id, name, surname);
        userDatabase.put(id, user);
        return user;  // Return the created user
    }

    @Override
    public User findUserById(UUID id) {
        return userDatabase.get(id);  // Retrieve user by UUID
    }

    @Override
    public boolean deleteUser(UUID id) {
        if (userDatabase.containsKey(id)) {
            userDatabase.remove(id);  // Remove user by UUID
            return true;
        }
        return false;  // Return false if user not found
    }
}
