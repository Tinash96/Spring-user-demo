package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String addUser(@RequestParam String name, @RequestParam String surname) {
        try {
            User user = userService.addUser(name, surname);
            logger.info("✅ Successfully added user: {} {} with ID {}", user.getName(), user.getSurname(), user.getId());
            return "User " + user.getName() + " " + user.getSurname() + " with ID " + user.getId() + " has been added.";
        } catch (Exception e) {
            logger.error("❌ Error adding user: {}", e.getMessage());
            return "Error adding user.";
        }
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable UUID id) {
        try {
            User user = userService.getUser(id);
            if (user != null) {
                logger.info("ℹ️ Successfully retrieved user: {} {} with ID {}", user.getName(), user.getSurname(), user.getId());
                return "User " + user.getName() + " " + user.getSurname() + " with ID " + user.getId();
            } else {
                logger.warn("⚠️ User with ID {} not found", id);
                return "User not found.";
            }
        } catch (Exception e) {
            logger.error("❌ Error retrieving user: {}", e.getMessage());
            return "Error retrieving user.";
        }
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable UUID id) {
        try {
            boolean isDeleted = userService.removeUser(id);
            if (isDeleted) {
                logger.info("🗑️ Successfully deleted user with ID {}", id);
                return "User with ID " + id + " has been removed.";
            } else {
                logger.warn("⚠️ User with ID {} not found for deletion", id);
                return "User with ID " + id + " not found.";
            }
        } catch (Exception e) {
            logger.error("❌ Error deleting user: {}", e.getMessage());
            return "Error deleting user.";
        }
    }
}
