package com.example.demo.service;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link UserController}.
 * Uses nested test classes to organize tests into logical categories:
 * - Successful operations
 * - User not found scenarios
 * - Exception handling
 */
class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UUID userId;
    private User user;

    /**
     * Sets up mock objects before each test case.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        user = new User(userId, "Tina", "Smith");
    }

    /**
     * Tests for successful user operations such as add, get, and delete.
     */
    @Nested
    class SuccessfulOperations {

        /**
         * Tests successful user creation.
         */
        @Test
        void testAddUser() {
            when(userService.addUser(anyString(), anyString())).thenReturn(user);
            String response = userController.addUser("Tina", "Smith");

            assertNotNull(response);
            assertTrue(response.contains("User Tina Smith with ID " + user.getId()));
        }

        /**
         * Tests successful user retrieval by ID.
         */
        @Test
        void testGetUser() {
            when(userService.getUser(userId)).thenReturn(user);
            String response = userController.getUser(userId);

            assertNotNull(response);
            assertTrue(response.contains("User Tina Smith with ID " + userId.toString()));
        }

        /**
         * Tests successful user deletion by ID.
         */
        @Test
        void testRemoveUser() {
            when(userService.removeUser(userId)).thenReturn(true);
            String response = userController.removeUser(userId);

            assertNotNull(response);
            assertTrue(response.contains("User with ID " + userId + " has been removed."));
        }
    }

    /**
     * Tests behavior when the user is not found in the system.
     */
    @Nested
    class UserNotFound {

        /**
         * Tests retrieval of a non-existent user.
         */
        @Test
        void testGetUser_NotFound() {
            when(userService.getUser(userId)).thenReturn(null);
            String response = userController.getUser(userId);

            assertNotNull(response);
            assertTrue(response.contains("User not found."));
        }

        /**
         * Tests deletion of a non-existent user.
         */
        @Test
        void testRemoveUser_NotFound() {
            when(userService.removeUser(userId)).thenReturn(false);
            String response = userController.removeUser(userId);

            assertNotNull(response);
            assertTrue(response.contains("User with ID " + userId + " not found."));
        }
    }

    /**
     * Tests exception handling during service method calls.
     */
    @Nested
    class ExceptionHandling {

        /**
         * Tests exception handling during user creation.
         */
        @Test
        void testAddUser_Exception() {
            when(userService.addUser(anyString(), anyString())).thenThrow(new RuntimeException("Database error"));
            String response = userController.addUser("Tina", "Smith");

            assertNotNull(response);
            assertTrue(response.contains("Error adding user."));
        }

        /**
         * Tests exception handling during user retrieval.
         */
        @Test
        void testGetUser_Exception() {
            when(userService.getUser(userId)).thenThrow(new RuntimeException("Internal error"));
            String response = userController.getUser(userId);

            assertNotNull(response);
            assertTrue(response.contains("Error retrieving user."));
        }

        /**
         * Tests exception handling during user deletion.
         */
        @Test
        void testRemoveUser_Exception() {
            when(userService.removeUser(userId)).thenThrow(new RuntimeException("Unexpected failure"));
            String response = userController.removeUser(userId);

            assertNotNull(response);
            assertTrue(response.contains("Error deleting user."));
        }
    }
}
