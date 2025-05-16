package com.example.demo.model;

import java.util.UUID;

/**
 * Represents a User with a unique identifier, name, and surname.
 */
public class User {
    private UUID id;
    private String name;
    private String surname;

    /**
     * Constructs a new User with the specified ID, name, and surname.
     *
     * @param id      the unique identifier of the user
     * @param name    the user's first name
     * @param surname the user's last name
     */
    public User(UUID id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return the user's UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the first name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the last name of the user.
     *
     * @return the user's surname
     */
    public String getSurname() {
        return surname;
    }
}
