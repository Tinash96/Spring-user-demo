package com.example.demo.service;

import com.example.demo.model.User;
import java.util.UUID;

/**
 * Service interface for user-related operations.
 */
public interface UserService {

    /**
     * Adds a new user with the given name and surname.
     *
     * @param name    the user's first name
     * @param surname the user's last name
     * @return the created User object
     */
    User addUser(String name, String surname);

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the UUID of the user to retrieve
     * @return the User object if found, or null if not found
     */
    User getUser(UUID id);

    /**
     * Removes a user identified by the given UUID.
     *
     * @param id the UUID of the user to remove
     * @return true if the user was successfully removed, false otherwise
     */
    boolean removeUser(UUID id);
}
