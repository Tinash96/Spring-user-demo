package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repo.FakeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTests {

    private FakeRepo fakeRepo;
    private UserServiceImpl userService;

    private UUID userId;
    private User user;

    @BeforeEach
    void setUp() {
        fakeRepo = new FakeRepo();
        userService = new UserServiceImpl(fakeRepo);
        userId = UUID.randomUUID(); // Correctly creating UUID
        user = new User(userId, "Tina", "Smith"); // Mock the User object
    }

    @Test
    void testAddUser() {
        User createdUser = userService.addUser("Tina", "Smith");
        assertNotNull(createdUser);
        assertEquals("Tina", createdUser.getName());
        assertEquals("Smith", createdUser.getSurname());
        assertNotNull(createdUser.getId()); // Check that the ID is generated
    }

    @Test
    void testGetUser() {
        // Add the user to FakeRepo first
        User createdUser = userService.addUser("Tina", "Smith");

        // Now use the ID to retrieve it
        User retrievedUser = userService.getUser(createdUser.getId());
        assertNotNull(retrievedUser);
        assertEquals(createdUser.getId(), retrievedUser.getId());
        assertEquals("Tina", retrievedUser.getName());
    }

    @Test
    void testRemoveUser() {
        // Add the user to FakeRepo first
        User createdUser = userService.addUser("Tina", "Smith");

        // Now test removal
        boolean isDeleted = userService.removeUser(createdUser.getId());
        assertTrue(isDeleted);
    }
}
