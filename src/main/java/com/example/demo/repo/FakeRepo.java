package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * FakeRepo is an in-memory implementation of {@link FakeRepoInterface}.
 * It uses a HashMap to simulate database operations for User entities.
 */
@Repository
public class FakeRepo implements FakeRepoInterface {

    /**
     * In-memory storage for User objects.
     * The key is a UUID representing the User ID.
     */
    private Map<UUID, User> userDatabase = new HashMap<>();

    /**
     * Inserts a new user with the provided name and surname.
     *
     * @param name    the first name of the user
     * @param surname the surname of the user
     * @return the created User object
     */
    @Override
    public User insertUser(String name, String surname) {
        UUID id = UUID.randomUUID();
        User user = new User(id, name, surname);
        userDatabase.put(id, user);
        return user;
    }

    /**
     * Finds a user by their UUID.
     *
     * @param id the UUID of the user to find
     * @return the User object if found, otherwise null
     */
    @Override
    public User findUserById(UUID id) {
        return userDatabase.get(id);
    }

    /**
     * Deletes a user by their UUID.
     *
     * @param id the UUID of the user to delete
     * @return true if the user was deleted, false if not found
     */
    @Override
    public boolean deleteUser(UUID id) {
        return userDatabase.remove(id) != null;
    }
}
