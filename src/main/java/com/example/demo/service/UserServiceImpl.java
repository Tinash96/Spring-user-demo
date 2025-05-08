package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repo.FakeRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final FakeRepoInterface fakeRepo;

    @Autowired
    public UserServiceImpl(FakeRepoInterface fakeRepo) {
        this.fakeRepo = fakeRepo;
    }

    @Override
    public User addUser(String name, String surname) {
        // Generate UUID inside the service method or let the repository do it
        User user = new User(UUID.randomUUID(), name, surname);  // Create user with UUID
        return fakeRepo.insertUser(user.getName(), user.getSurname());  // Insert user into repo
    }

    @Override
    public User getUser(UUID id) {
        return fakeRepo.findUserById(id);  // Find user by UUID
    }

    @Override
    public boolean removeUser(UUID id) {
        return fakeRepo.deleteUser(id);  // Delete user by UUID
    }
}
