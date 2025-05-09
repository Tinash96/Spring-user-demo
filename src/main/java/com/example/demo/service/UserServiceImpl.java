package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repo.FakeRepoInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    // Dependency Injection: Inject FakeRepoInterface to handle data persistence
    private final FakeRepoInterface fakeRepo;

    // Logger instance to log the actions performed by the service
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    // Constructor to initialize the UserService with the injected FakeRepoInterface
    public UserServiceImpl(FakeRepoInterface fakeRepo) {
        this.fakeRepo = fakeRepo;
    }

    @Override
    // Method to add a new user with the provided name and surname
    public User addUser(String name, String surname) {
        // Create a new User object with a randomly generated UUID and provided name & surname
        User user = new User(UUID.randomUUID(), name, surname);

        // Log the action of adding the user
        logger.info("✅ Adding user: {} {}", name, surname);

        // Insert the created user into the repository and retrieve the added user
        User addedUser = fakeRepo.insertUser(user.getName(), user.getSurname());

        // Log the successful addition of the user
        logger.info("✅ Added user: {} {} with ID {}", name, surname, addedUser.getId());

        // Return the added user object
        return addedUser;
    }

    @Override
    // Method to retrieve a user by their unique ID
    public User getUser(UUID id) {
        // Find the user from the repository by their UUID
        User user = fakeRepo.findUserById(id);

        // If the user is found, log the details
        if (user != null) {
            logger.info("ℹ️ Retrieved user: {} {} with ID {}", user.getName(), user.getSurname(), id);
        } else {
            // If the user is not found, log a warning
            logger.warn("⚠️ User with ID {} not found", id);
        }

        // Return the found user or null if not found
        return user;
    }

    @Override
    // Method to delete a user by their unique ID
    public boolean removeUser(UUID id) {
        // Attempt to delete the user from the repository
        boolean isDeleted = fakeRepo.deleteUser(id);

        // Log the result of the deletion attempt
        if (isDeleted) {
            logger.info("🗑️ Deleted user with ID {}", id);
        } else {
            // If no user was found to delete, log a warning
            logger.warn("⚠️ No user found with ID {} to delete", id);
        }

        // Return whether the user was successfully deleted or not
        return isDeleted;
    }
}
