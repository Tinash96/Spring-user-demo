package com.example.demo.repo;

import com.example.demo.model.User;
import java.util.UUID;

/**
 * Interface for a fake user repository that simulates basic
 * CRUD operations on an in-memory data store.
 */
public interface FakeRepoInterface {

    /**
     * Inserts a new user with the specified name and surname.
     *
     * @param name    the user's first name
     * @param surname the user's last name
     * @return the newly created User object
     */
    User insertUser(String name, String surname);

    /**
     * Finds and returns a user by their UUID.
     *
     * @param id the UUID of the user to find
     * @return the User object if found, or null if not found
     */
    User findUserById(UUID id);

    /**
     * Deletes a user from the repository by their UUID.
     *
     * @param id the UUID of the user to delete
     * @return true if the user was successfully deleted, false if not found
     */
    boolean deleteUser(UUID id);
}
