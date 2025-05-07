package com.example.demo.service;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UUID userId;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();  // Generate a unique ID for testing
        user = new User(userId, "Tina", "Smith"); // Mock the User object with an ID
    }

    @Test
    void testAddUser() {
        // Mock the addUser method in the service
        when(userService.addUser(anyString(), anyString())).thenReturn(user);

        String response = userController.addUser("Tina", "Smith");

        assertNotNull(response);
        assertTrue(response.contains("User Tina Smith with ID " + userId));
    }

    @Test
    void testGetUser() {
        // Mock the getUser method in the service
        when(userService.getUser(userId)).thenReturn(user);

        String response = userController.getUser(userId);

        assertNotNull(response);
        assertTrue(response.contains("User Tina Smith with ID " + userId.toString()));
    }

    @Test
    void testRemoveUser() {
        // Mock the removeUser method in the service
        when(userService.removeUser(userId)).thenReturn(true);

        String response = userController.removeUser(userId);

        assertNotNull(response);
        assertTrue(response.contains("User with ID " + userId.toString() + " has been removed."));
    }
}
