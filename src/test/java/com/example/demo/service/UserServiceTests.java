package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTests {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    void testAddUser() {
        User user = userService.addUser("Tina", "Smith");
        assertNotNull(user);
        assertEquals("Tina", user.getName());
        assertEquals("Smith", user.getSurname());
        assertNotNull(user.getId());
    }

    @Test
    void testGetUser() {
        User created = userService.addUser("Tina", "Smith");
        User found = userService.getUser(created.getId());
        assertEquals(created.getId(), found.getId());
        assertEquals("Tina", found.getName());
    }

    @Test
    void testRemoveUser() {
        User created = userService.addUser("Tina", "Smith");
        boolean removed = userService.removeUser(created.getId());
        assertTrue(removed);
        assertNull(userService.getUser(created.getId()));
    }

    @Test
    void testGetUser_NotFound() {
        User user = userService.getUser("non-existent-id");
        assertNull(user);
    }

    @Test
    void testRemoveUser_NotFound() {
        boolean removed = userService.removeUser("invalid-id");
        assertFalse(removed);
    }

    @Test
    void testAddMultipleUsers() {
        userService.addUser("Tina", "Smith");
        userService.addUser("John", "Doe");
        userService.addUser("Jane", "Doe");

        User jane = userService.addUser("Jane", "Doe");
        assertNotNull(jane.getId());
    }
}


