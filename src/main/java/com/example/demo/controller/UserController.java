package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String addUser(@RequestParam String name, @RequestParam String surname) {
        User user = userService.addUser(name, surname);
        return "User " + user.getName() + " " + user.getSurname() + " with ID " + user.getId() + " has been added.";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable UUID id) {
        User user = userService.getUser(id);
        return "User " + user.getName() + " " + user.getSurname() + " with ID " + user.getId();
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable UUID id) {
        boolean isDeleted = userService.removeUser(id);
        if (isDeleted) {
            return "User with ID " + id + " has been removed.";
        } else {
            return "User with ID " + id + " not found.";
        }
    }
}
