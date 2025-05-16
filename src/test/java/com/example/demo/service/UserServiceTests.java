package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repo.FakeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link UserServiceImpl} class.
 * This class uses a fake repository to test business logic
 * related to adding, retrieving, and removing users.
 */
class UserServiceTests {

    private FakeRepo fakeRepo;
    private UserServiceImpl userService;

    private UUID userId;
    private User user;

    /**
     * Initializes the test environment before each test method is executed.
     * Sets up the fake repository and the service implementation.
     */
    @BeforeEach
    void setUp() {
        fakeRepo = new FakeRepo();
        userService = new UserServiceImpl(fakeRepo);
        userId = UUID.randomUUID();
        user = new User(userId, "Tina", "Smith");
    }

    /**
     * Tests the {@link UserServiceImpl#addUser(String, String)} method.
     * Verifies that the user is successfully added and all user fields are correctly populated.
     */
    @Test
    void testAddUser() {
        User createdUser = userService.addUser("Tina", "Smith");
        assertNotNull(createdUser);
        assertEquals("Tina", createdUser.getName());
        assertEquals("Smith", createdUser.getSurname());
        assertNotNull(createdUser.getId());
    }

    /**
     * Tests the {@link UserServiceImpl#getUser(UUID)} method.
     * Verifies that a user can be retrieved by their unique ID after being added.
     */
    @Test
    void testGetUser() {
        User createdUser = userService.addUser("Tina", "Smith");
        User retrievedUser = userService.getUser(createdUser.getId());
        assertNotNull(retrievedUser);
        assertEquals(createdUser.getId(), retrievedUser.getId());
        assertEquals("Tina", retrievedUser.getName());
    }

    /**
     * Tests the {@link UserServiceImpl#removeUser(UUID)} method.
     * Verifies that a user can be successfully removed from the system.
     */
    @Test
    void testRemoveUser() {
        User createdUser = userService.addUser("Tina", "Smith");
        boolean isDeleted = userService.removeUser(createdUser.getId());
        assertTrue(isDeleted);
    }
}
